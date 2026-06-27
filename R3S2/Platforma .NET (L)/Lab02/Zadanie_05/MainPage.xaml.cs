using System.Collections.ObjectModel;

namespace Zadanie_05;

public partial class MainPage : ContentPage
{
    private readonly ObservableCollection<string> _destItems;
    private readonly ObservableCollection<string> _sourceItems;

    public MainPage()
    {
        InitializeComponent();

        _sourceItems = new ObservableCollection<string>
        {
            "Jablko", "Banan", "Czeresnia", "Gruszka", "Malina",
            "Pomarancza", "Sliwka", "Wisnia", "Truskawka", "Arbuz"
        };
        _destItems = new ObservableCollection<string>();

        SourceList.ItemsSource = _sourceItems;
        DestList.ItemsSource = _destItems;
        UpdateInfo();
    }

    private void OnItemSelected(object? sender, SelectedItemChangedEventArgs e)
    {
        UpdateInfo();
    }

    private void OnAdd(object? sender, EventArgs e)
    {
        if (!string.IsNullOrWhiteSpace(AddEntry.Text))
        {
            _sourceItems.Add(AddEntry.Text.Trim());
            AddEntry.Text = "";
            UpdateInfo();
        }
    }

    private void OnRemove(object? sender, EventArgs e)
    {
        if (SourceList.SelectedItem is string item)
        {
            _sourceItems.Remove(item);
            UpdateInfo();
        }
    }

    private void OnClear(object? sender, EventArgs e)
    {
        _sourceItems.Clear();
        UpdateInfo();
    }

    private void OnSort(object? sender, EventArgs e)
    {
        var sorted = _sourceItems.OrderBy(x => x).ToList();
        _sourceItems.Clear();
        foreach (var item in sorted) _sourceItems.Add(item);
        UpdateInfo();
    }

    private void OnMoveUp(object? sender, EventArgs e)
    {
        MoveItem(-1);
    }

    private void OnMoveDown(object? sender, EventArgs e)
    {
        MoveItem(1);
    }

    private void MoveItem(int direction)
    {
        if (SourceList.SelectedItem is not string selected) return;
        var idx = _sourceItems.IndexOf(selected);
        if (idx < 0) return;
        var newIdx = idx + direction;
        if (newIdx < 0 || newIdx >= _sourceItems.Count) return;
        _sourceItems.Move(idx, newIdx);
    }

    private async void OnSearch(object? sender, EventArgs e)
    {
        var query = SearchEntry.Text?.Trim().ToLower();
        if (string.IsNullOrEmpty(query)) return;
        var found = _sourceItems.FirstOrDefault(x => x.ToLower().Contains(query));
        if (found != null)
            SourceList.SelectedItem = found;
        else
            await DisplayAlertAsync("Szukaj", "Nie znaleziono elementu.", "OK");
    }

    private void OnTransferRight(object? sender, EventArgs e)
    {
        if (SourceList.SelectedItem is string item)
        {
            _sourceItems.Remove(item);
            _destItems.Add(item);
            UpdateInfo();
        }
    }

    private void OnTransferLeft(object? sender, EventArgs e)
    {
        if (DestList.SelectedItem is string item)
        {
            _destItems.Remove(item);
            _sourceItems.Add(item);
            UpdateInfo();
        }
    }

    private void UpdateInfo()
    {
        var selected = SourceList.SelectedItem as string ?? "(brak)";
        InfoLabel.Text = $"Elementow: {_sourceItems.Count} | Zaznaczony: {selected}";
    }
}