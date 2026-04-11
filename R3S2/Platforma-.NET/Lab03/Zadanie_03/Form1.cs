using System;
using System.Numerics;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Forms;

namespace Zadanie_03
{
    public partial class Form1 : Form
    {
        private BigInteger n, e, d;
        private bool kluczeWygenerowane = false;

        public Form1()
        {
            InitializeComponent();
        }

        private void btnGenerujKlucze_Click(object sender, EventArgs e_arg)
        {
            BigInteger p = GenerujLiczbePierwsza(512);
            BigInteger q = GenerujLiczbePierwsza(512);

            n = p * q;
            BigInteger phi = (p - 1) * (q - 1);

            e = 65537;

            d = ModInverse(e, phi);

            txtKluczPubliczny.Text = $"e = {e}\r\nn = {n}";
            txtKluczPrywatny.Text = $"d = {d}\r\nn = {n}";

            kluczeWygenerowane = true;
            lblStatus.Text = "Klucze RSA wygenerowane pomyślnie.";
        }

        private void btnSzyfruj_Click(object sender, EventArgs e_arg)
        {
            if (!kluczeWygenerowane)
            {
                MessageBox.Show("Najpierw wygeneruj klucze RSA.", "Błąd",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (string.IsNullOrEmpty(txtWejscie.Text))
            {
                MessageBox.Show("Podaj tekst do zaszyfrowania.", "Błąd",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            byte[] bajty = Encoding.UTF8.GetBytes(txtWejscie.Text);
            BigInteger wiadomosc = new BigInteger(bajty);

            if (wiadomosc >= n)
            {
                MessageBox.Show("Wiadomość jest za długa dla wygenerowanych kluczy.", "Błąd",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            BigInteger zaszyfrowana = BigInteger.ModPow(wiadomosc, e, n);
            txtWyjscie.Text = zaszyfrowana.ToString();
            lblStatus.Text = "Tekst zaszyfrowany.";
        }

        private void btnDeszyfruj_Click(object sender, EventArgs e_arg)
        {
            if (!kluczeWygenerowane)
            {
                MessageBox.Show("Najpierw wygeneruj klucze RSA.", "Błąd",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            BigInteger zaszyfrowana;
            if (!BigInteger.TryParse(txtWyjscie.Text, out zaszyfrowana))
            {
                MessageBox.Show("Pole wyjścia nie zawiera poprawnej liczby.", "Błąd",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            BigInteger odszyfrowana = BigInteger.ModPow(zaszyfrowana, d, n);
            byte[] bajty = odszyfrowana.ToByteArray();
            string tekst = Encoding.UTF8.GetString(bajty);

            txtWejscie.Text = tekst;
            lblStatus.Text = "Tekst odszyfrowany.";
        }

        private BigInteger GenerujLiczbePierwsza(int bity)
        {
            using (var rng = new RNGCryptoServiceProvider())
            {
                byte[] bufor = new byte[bity / 8];
                while (true)
                {
                    rng.GetBytes(bufor);
                    bufor[bufor.Length - 1] &= 0x7F; // zapewnia dodatniość
                    bufor[0] |= 0x01; // zapewnia nieparzystość

                    BigInteger kandydat = new BigInteger(bufor);
                    if (kandydat > 2 && CzyPrawdopodobniePierwsza(kandydat, 20))
                        return kandydat;
                }
            }
        }

        private bool CzyPrawdopodobniePierwsza(BigInteger n, int iteracje)
        {
            if (n < 2) return false;
            if (n == 2 || n == 3) return true;
            if (n % 2 == 0) return false;

            // Test Millera-Rabina
            BigInteger d_val = n - 1;
            int r = 0;
            while (d_val % 2 == 0)
            {
                d_val /= 2;
                r++;
            }

            using (var rng = new RNGCryptoServiceProvider())
            {
                byte[] bufor = new byte[n.ToByteArray().Length];
                for (int i = 0; i < iteracje; i++)
                {
                    BigInteger a;
                    do
                    {
                        rng.GetBytes(bufor);
                        bufor[bufor.Length - 1] &= 0x7F;
                        a = new BigInteger(bufor);
                    } while (a < 2 || a >= n - 2);

                    BigInteger x = BigInteger.ModPow(a, d_val, n);

                    if (x == 1 || x == n - 1) continue;

                    bool znaleziono = false;
                    for (int j = 0; j < r - 1; j++)
                    {
                        x = BigInteger.ModPow(x, 2, n);
                        if (x == n - 1)
                        {
                            znaleziono = true;
                            break;
                        }
                    }

                    if (!znaleziono) return false;
                }
            }

            return true;
        }

        private BigInteger ModInverse(BigInteger a, BigInteger m)
        {
            BigInteger g, x, y;
            ExtGcd(a, m, out g, out x, out y);
            return ((x % m) + m) % m;
        }

        private void ExtGcd(BigInteger a, BigInteger b, out BigInteger g, out BigInteger x, out BigInteger y)
        {
            if (a == 0)
            {
                g = b; x = 0; y = 1;
                return;
            }
            BigInteger g1, x1, y1;
            ExtGcd(b % a, a, out g1, out x1, out y1);
            g = g1;
            x = y1 - (b / a) * x1;
            y = x1;
        }
    }
}
