using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Mail;
using System.Windows.Forms;

namespace Zadanie_06
{
    public partial class Form1 : Form
    {
        private List<string> zalaczniki = new List<string>();

        public Form1()
        {
            InitializeComponent();
        }

        private void btnDodajZalacznik_Click(object sender, EventArgs e)
        {
            using (var dialog = new OpenFileDialog())
            {
                dialog.Multiselect = true;
                dialog.Title = "Wybierz załączniki";
                if (dialog.ShowDialog() == DialogResult.OK)
                {
                    foreach (string plik in dialog.FileNames)
                    {
                        zalaczniki.Add(plik);
                        lstZalaczniki.Items.Add(System.IO.Path.GetFileName(plik));
                    }
                }
            }
        }

        private void btnUsunZalacznik_Click(object sender, EventArgs e)
        {
            if (lstZalaczniki.SelectedIndex >= 0)
            {
                int idx = lstZalaczniki.SelectedIndex;
                zalaczniki.RemoveAt(idx);
                lstZalaczniki.Items.RemoveAt(idx);
            }
        }

        private void btnWyslij_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtSmtp.Text) ||
                string.IsNullOrWhiteSpace(txtPort.Text) ||
                string.IsNullOrWhiteSpace(txtLogin.Text) ||
                string.IsNullOrWhiteSpace(txtHaslo.Text) ||
                string.IsNullOrWhiteSpace(txtDo.Text) ||
                string.IsNullOrWhiteSpace(txtTemat.Text))
            {
                MessageBox.Show("Wypełnij wszystkie wymagane pola.", "Błąd",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                var wiadomosc = new MailMessage();
                wiadomosc.From = new MailAddress(txtLogin.Text);
                wiadomosc.To.Add(txtDo.Text);
                wiadomosc.Subject = txtTemat.Text;
                wiadomosc.Body = txtTresc.Text;
                wiadomosc.IsBodyHtml = chkHtml.Checked;

                foreach (string plik in zalaczniki)
                    wiadomosc.Attachments.Add(new Attachment(plik));

                int port;
                if (!int.TryParse(txtPort.Text, out port))
                {
                    MessageBox.Show("Nieprawidłowy numer portu.", "Błąd",
                        MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                var smtp = new SmtpClient(txtSmtp.Text, port);
                smtp.EnableSsl = chkSsl.Checked;
                smtp.Credentials = new NetworkCredential(txtLogin.Text, txtHaslo.Text);

                smtp.Send(wiadomosc);

                MessageBox.Show("Wiadomość wysłana pomyślnie!", "Sukces",
                    MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Błąd wysyłania:\n{ex.Message}", "Błąd",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
