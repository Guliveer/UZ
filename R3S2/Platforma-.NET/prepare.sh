#!/usr/bin/env bash
# ============================================================
# Shell port of prepare.bat for macOS / Linux.
# Copies a selected LabXX folder, renames it per the course
# naming convention, strips bin/obj/.claude/.remember, rewrites
# the solution filename and in-file references, then compresses
# the result into a .7z archive with maximum compression.
#
# Uses any available 7-Zip CLI: 7zz, 7z, 7za. Falls back to
# the keka7zz binary bundled with Keka.app on macOS.
# ============================================================

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# ---- locate a 7z-compatible CLI -----------------------------
find_sevenzip() {
    for cmd in 7zz 7z 7za; do
        if command -v "$cmd" >/dev/null 2>&1; then
            echo "$cmd"
            return 0
        fi
    done
    local keka="/Applications/Keka.app/Contents/MacOS/keka7zz"
    if [[ -x "$keka" ]]; then
        echo "$keka"
        return 0
    fi
    return 1
}

if ! SEVENZIP="$(find_sevenzip)"; then
    echo "Error: no 7z-compatible tool found (7zz / 7z / 7za / Keka)." >&2
    echo "Install p7zip (brew install sevenzip) or Keka.app." >&2
    exit 1
fi

# ---- list Lab?? folders -------------------------------------
echo
echo "Available Lab folders:"
echo "----------------------"

labs=()
for d in Lab??; do
    [[ -d "$d" ]] || continue
    labs+=("$d")
done

if [[ ${#labs[@]} -eq 0 ]]; then
    echo "No Lab folders found."
    exit 1
fi

for i in "${!labs[@]}"; do
    printf "%d. %s\n" "$((i + 1))" "${labs[i]}"
done

echo
read -r -p "Select a lab by number: " choice

if ! [[ "$choice" =~ ^[0-9]+$ ]] || (( choice < 1 || choice > ${#labs[@]} )); then
    echo "Invalid selection." >&2
    exit 1
fi

selectedLab="${labs[$((choice - 1))]}"
echo "Selected: $selectedLab"

# last two characters of the lab name, e.g. "04"
labNumber="${selectedLab: -2}"

echo
read -r -p "Enter your first name: " firstName
read -r -p "Enter your last name: "  lastName

newName="Plat-dot-NET-34INF-SSI-SP_LAB_${labNumber}_${firstName}_${lastName}"

# ---- copy ---------------------------------------------------
echo
echo "Creating copy..."

if [[ -e "$newName" ]]; then
    echo "Target '$newName' already exists — remove it first." >&2
    exit 1
fi

cp -R "$selectedLab" "$newName"

# ---- prune build artifacts ---------------------------------
echo "Removing bin/, obj/, .claude/ and .remember/ folders..."

find "$newName" -type d \( -name bin -o -name obj -o -name .claude -o -name .remember \) \
    -prune -exec rm -rf {} +

# ---- rename solution file ----------------------------------
echo "Renaming solution file..."

solutionRenamed=false
for ext in sln slnx; do
    src="$newName/$selectedLab.$ext"
    if [[ -f "$src" ]]; then
        mv "$src" "$newName/$newName.$ext"
        solutionRenamed=true
    fi
done

if [[ "$solutionRenamed" == false ]]; then
    while IFS= read -r -d '' f; do
        mv "$f" "$(dirname "$f")/$newName.${f##*.}"
        solutionRenamed=true
        break
    done < <(find "$newName" -type f \( -name "*.sln" -o -name "*.slnx" \) -print0)
fi

# ---- rewrite occurrences inside text files -----------------
echo "Replacing occurrences in text files..."

# escape selectedLab for sed (safe: only letters+digits in practice,
# but we still guard against regex metacharacters)
escaped_old=$(printf '%s\n' "$selectedLab" | sed 's/[.[\*^$/]/\\&/g')
escaped_new=$(printf '%s\n' "$newName"     | sed 's/[&/\]/\\&/g')

find "$newName" -type f \( \
        -name "*.sln"    -o -name "*.slnx"  -o -name "*.csproj" -o \
        -name "*.cs"     -o -name "*.config" -o -name "*.json"  -o \
        -name "*.xml"    -o -name "*.txt" \
    \) -exec sed -i '' "s/${escaped_old}/${escaped_new}/g" {} +

# ---- compress ----------------------------------------------
echo "Creating archive with maximum compression (using: $SEVENZIP)..."

rm -f "$newName.7z"
"$SEVENZIP" a -t7z "$newName.7z" "$newName" -mx=9 -mmt=on -ms=on >/dev/null

echo
echo "Done successfully!"
echo "Output:"
echo "- Folder:  $newName"
echo "- Archive: $newName.7z"
