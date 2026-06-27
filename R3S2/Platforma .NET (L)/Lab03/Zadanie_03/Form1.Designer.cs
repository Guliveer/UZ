namespace Zadanie_03
{
    partial class Form1
    {
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Kod generowany przez Projektanta formularzy systemu Windows

        private void InitializeComponent()
        {
            this.btnGenerujKlucze = new System.Windows.Forms.Button();
            this.btnSzyfruj = new System.Windows.Forms.Button();
            this.btnDeszyfruj = new System.Windows.Forms.Button();
            this.txtWejscie = new System.Windows.Forms.TextBox();
            this.txtWyjscie = new System.Windows.Forms.TextBox();
            this.txtKluczPubliczny = new System.Windows.Forms.TextBox();
            this.txtKluczPrywatny = new System.Windows.Forms.TextBox();
            this.lblWejscie = new System.Windows.Forms.Label();
            this.lblWyjscie = new System.Windows.Forms.Label();
            this.lblPubliczny = new System.Windows.Forms.Label();
            this.lblPrywatny = new System.Windows.Forms.Label();
            this.lblStatus = new System.Windows.Forms.Label();
            this.SuspendLayout();
            //
            // lblWejscie
            //
            this.lblWejscie.AutoSize = true;
            this.lblWejscie.Location = new System.Drawing.Point(12, 15);
            this.lblWejscie.Text = "Tekst jawny:";
            //
            // txtWejscie
            //
            this.txtWejscie.Location = new System.Drawing.Point(12, 35);
            this.txtWejscie.Multiline = true;
            this.txtWejscie.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtWejscie.Size = new System.Drawing.Size(360, 60);
            //
            // lblWyjscie
            //
            this.lblWyjscie.AutoSize = true;
            this.lblWyjscie.Location = new System.Drawing.Point(12, 105);
            this.lblWyjscie.Text = "Tekst zaszyfrowany:";
            //
            // txtWyjscie
            //
            this.txtWyjscie.Location = new System.Drawing.Point(12, 125);
            this.txtWyjscie.Multiline = true;
            this.txtWyjscie.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtWyjscie.Size = new System.Drawing.Size(360, 60);
            //
            // btnGenerujKlucze
            //
            this.btnGenerujKlucze.Location = new System.Drawing.Point(388, 35);
            this.btnGenerujKlucze.Size = new System.Drawing.Size(180, 30);
            this.btnGenerujKlucze.Text = "Generuj klucze RSA";
            this.btnGenerujKlucze.UseVisualStyleBackColor = true;
            this.btnGenerujKlucze.Click += new System.EventHandler(this.btnGenerujKlucze_Click);
            //
            // btnSzyfruj
            //
            this.btnSzyfruj.Location = new System.Drawing.Point(388, 75);
            this.btnSzyfruj.Size = new System.Drawing.Size(180, 30);
            this.btnSzyfruj.Text = "Szyfruj";
            this.btnSzyfruj.UseVisualStyleBackColor = true;
            this.btnSzyfruj.Click += new System.EventHandler(this.btnSzyfruj_Click);
            //
            // btnDeszyfruj
            //
            this.btnDeszyfruj.Location = new System.Drawing.Point(388, 115);
            this.btnDeszyfruj.Size = new System.Drawing.Size(180, 30);
            this.btnDeszyfruj.Text = "Deszyfruj";
            this.btnDeszyfruj.UseVisualStyleBackColor = true;
            this.btnDeszyfruj.Click += new System.EventHandler(this.btnDeszyfruj_Click);
            //
            // lblPubliczny
            //
            this.lblPubliczny.AutoSize = true;
            this.lblPubliczny.Location = new System.Drawing.Point(12, 200);
            this.lblPubliczny.Text = "Klucz publiczny (e, n):";
            //
            // txtKluczPubliczny
            //
            this.txtKluczPubliczny.Location = new System.Drawing.Point(12, 220);
            this.txtKluczPubliczny.Multiline = true;
            this.txtKluczPubliczny.ReadOnly = true;
            this.txtKluczPubliczny.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtKluczPubliczny.Size = new System.Drawing.Size(556, 70);
            //
            // lblPrywatny
            //
            this.lblPrywatny.AutoSize = true;
            this.lblPrywatny.Location = new System.Drawing.Point(12, 300);
            this.lblPrywatny.Text = "Klucz prywatny (d, n):";
            //
            // txtKluczPrywatny
            //
            this.txtKluczPrywatny.Location = new System.Drawing.Point(12, 320);
            this.txtKluczPrywatny.Multiline = true;
            this.txtKluczPrywatny.ReadOnly = true;
            this.txtKluczPrywatny.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtKluczPrywatny.Size = new System.Drawing.Size(556, 70);
            //
            // lblStatus
            //
            this.lblStatus.AutoSize = true;
            this.lblStatus.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Italic);
            this.lblStatus.Location = new System.Drawing.Point(12, 400);
            this.lblStatus.Text = "Wygeneruj klucze, aby rozpocząć.";
            //
            // Form1
            //
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(584, 431);
            this.Controls.Add(this.lblWejscie);
            this.Controls.Add(this.txtWejscie);
            this.Controls.Add(this.lblWyjscie);
            this.Controls.Add(this.txtWyjscie);
            this.Controls.Add(this.btnGenerujKlucze);
            this.Controls.Add(this.btnSzyfruj);
            this.Controls.Add(this.btnDeszyfruj);
            this.Controls.Add(this.lblPubliczny);
            this.Controls.Add(this.txtKluczPubliczny);
            this.Controls.Add(this.lblPrywatny);
            this.Controls.Add(this.txtKluczPrywatny);
            this.Controls.Add(this.lblStatus);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "RSA - BigInteger Demo";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Button btnGenerujKlucze;
        private System.Windows.Forms.Button btnSzyfruj;
        private System.Windows.Forms.Button btnDeszyfruj;
        private System.Windows.Forms.TextBox txtWejscie;
        private System.Windows.Forms.TextBox txtWyjscie;
        private System.Windows.Forms.TextBox txtKluczPubliczny;
        private System.Windows.Forms.TextBox txtKluczPrywatny;
        private System.Windows.Forms.Label lblWejscie;
        private System.Windows.Forms.Label lblWyjscie;
        private System.Windows.Forms.Label lblPubliczny;
        private System.Windows.Forms.Label lblPrywatny;
        private System.Windows.Forms.Label lblStatus;
    }
}
