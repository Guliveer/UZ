namespace Zadanie_06
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
            this.grpSmtp = new System.Windows.Forms.GroupBox();
            this.txtSmtp = new System.Windows.Forms.TextBox();
            this.txtPort = new System.Windows.Forms.TextBox();
            this.txtLogin = new System.Windows.Forms.TextBox();
            this.txtHaslo = new System.Windows.Forms.TextBox();
            this.lblSmtp = new System.Windows.Forms.Label();
            this.lblPort = new System.Windows.Forms.Label();
            this.lblLogin = new System.Windows.Forms.Label();
            this.lblHaslo = new System.Windows.Forms.Label();
            this.chkSsl = new System.Windows.Forms.CheckBox();
            this.grpWiadomosc = new System.Windows.Forms.GroupBox();
            this.txtDo = new System.Windows.Forms.TextBox();
            this.txtTemat = new System.Windows.Forms.TextBox();
            this.txtTresc = new System.Windows.Forms.TextBox();
            this.lblDo = new System.Windows.Forms.Label();
            this.lblTemat = new System.Windows.Forms.Label();
            this.lblTresc = new System.Windows.Forms.Label();
            this.chkHtml = new System.Windows.Forms.CheckBox();
            this.grpZalaczniki = new System.Windows.Forms.GroupBox();
            this.lstZalaczniki = new System.Windows.Forms.ListBox();
            this.btnDodajZalacznik = new System.Windows.Forms.Button();
            this.btnUsunZalacznik = new System.Windows.Forms.Button();
            this.btnWyslij = new System.Windows.Forms.Button();
            this.grpSmtp.SuspendLayout();
            this.grpWiadomosc.SuspendLayout();
            this.grpZalaczniki.SuspendLayout();
            this.SuspendLayout();
            //
            // grpSmtp
            //
            this.grpSmtp.Controls.Add(this.lblSmtp);
            this.grpSmtp.Controls.Add(this.txtSmtp);
            this.grpSmtp.Controls.Add(this.lblPort);
            this.grpSmtp.Controls.Add(this.txtPort);
            this.grpSmtp.Controls.Add(this.lblLogin);
            this.grpSmtp.Controls.Add(this.txtLogin);
            this.grpSmtp.Controls.Add(this.lblHaslo);
            this.grpSmtp.Controls.Add(this.txtHaslo);
            this.grpSmtp.Controls.Add(this.chkSsl);
            this.grpSmtp.Location = new System.Drawing.Point(12, 12);
            this.grpSmtp.Size = new System.Drawing.Size(360, 165);
            this.grpSmtp.Text = "Ustawienia SMTP";
            //
            // lblSmtp
            //
            this.lblSmtp.AutoSize = true;
            this.lblSmtp.Location = new System.Drawing.Point(10, 25);
            this.lblSmtp.Text = "Serwer SMTP:";
            //
            // txtSmtp
            //
            this.txtSmtp.Location = new System.Drawing.Point(110, 22);
            this.txtSmtp.Size = new System.Drawing.Size(235, 20);
            this.txtSmtp.Text = "smtp.gmail.com";
            //
            // lblPort
            //
            this.lblPort.AutoSize = true;
            this.lblPort.Location = new System.Drawing.Point(10, 55);
            this.lblPort.Text = "Port:";
            //
            // txtPort
            //
            this.txtPort.Location = new System.Drawing.Point(110, 52);
            this.txtPort.Size = new System.Drawing.Size(80, 20);
            this.txtPort.Text = "587";
            //
            // lblLogin
            //
            this.lblLogin.AutoSize = true;
            this.lblLogin.Location = new System.Drawing.Point(10, 85);
            this.lblLogin.Text = "Login (e-mail):";
            //
            // txtLogin
            //
            this.txtLogin.Location = new System.Drawing.Point(110, 82);
            this.txtLogin.Size = new System.Drawing.Size(235, 20);
            //
            // lblHaslo
            //
            this.lblHaslo.AutoSize = true;
            this.lblHaslo.Location = new System.Drawing.Point(10, 115);
            this.lblHaslo.Text = "Hasło:";
            //
            // txtHaslo
            //
            this.txtHaslo.Location = new System.Drawing.Point(110, 112);
            this.txtHaslo.Size = new System.Drawing.Size(235, 20);
            this.txtHaslo.UseSystemPasswordChar = true;
            //
            // chkSsl
            //
            this.chkSsl.AutoSize = true;
            this.chkSsl.Checked = true;
            this.chkSsl.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkSsl.Location = new System.Drawing.Point(110, 140);
            this.chkSsl.Text = "Użyj SSL/TLS";
            //
            // grpWiadomosc
            //
            this.grpWiadomosc.Controls.Add(this.lblDo);
            this.grpWiadomosc.Controls.Add(this.txtDo);
            this.grpWiadomosc.Controls.Add(this.lblTemat);
            this.grpWiadomosc.Controls.Add(this.txtTemat);
            this.grpWiadomosc.Controls.Add(this.lblTresc);
            this.grpWiadomosc.Controls.Add(this.txtTresc);
            this.grpWiadomosc.Controls.Add(this.chkHtml);
            this.grpWiadomosc.Location = new System.Drawing.Point(12, 185);
            this.grpWiadomosc.Size = new System.Drawing.Size(360, 230);
            this.grpWiadomosc.Text = "Wiadomość";
            //
            // lblDo
            //
            this.lblDo.AutoSize = true;
            this.lblDo.Location = new System.Drawing.Point(10, 25);
            this.lblDo.Text = "Do:";
            //
            // txtDo
            //
            this.txtDo.Location = new System.Drawing.Point(70, 22);
            this.txtDo.Size = new System.Drawing.Size(275, 20);
            //
            // lblTemat
            //
            this.lblTemat.AutoSize = true;
            this.lblTemat.Location = new System.Drawing.Point(10, 55);
            this.lblTemat.Text = "Temat:";
            //
            // txtTemat
            //
            this.txtTemat.Location = new System.Drawing.Point(70, 52);
            this.txtTemat.Size = new System.Drawing.Size(275, 20);
            //
            // lblTresc
            //
            this.lblTresc.AutoSize = true;
            this.lblTresc.Location = new System.Drawing.Point(10, 85);
            this.lblTresc.Text = "Treść:";
            //
            // txtTresc
            //
            this.txtTresc.Location = new System.Drawing.Point(70, 82);
            this.txtTresc.Multiline = true;
            this.txtTresc.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtTresc.Size = new System.Drawing.Size(275, 110);
            //
            // chkHtml
            //
            this.chkHtml.AutoSize = true;
            this.chkHtml.Location = new System.Drawing.Point(70, 200);
            this.chkHtml.Text = "Treść HTML";
            //
            // grpZalaczniki
            //
            this.grpZalaczniki.Controls.Add(this.lstZalaczniki);
            this.grpZalaczniki.Controls.Add(this.btnDodajZalacznik);
            this.grpZalaczniki.Controls.Add(this.btnUsunZalacznik);
            this.grpZalaczniki.Location = new System.Drawing.Point(388, 12);
            this.grpZalaczniki.Size = new System.Drawing.Size(210, 230);
            this.grpZalaczniki.Text = "Załączniki";
            //
            // lstZalaczniki
            //
            this.lstZalaczniki.Location = new System.Drawing.Point(10, 22);
            this.lstZalaczniki.Size = new System.Drawing.Size(190, 160);
            //
            // btnDodajZalacznik
            //
            this.btnDodajZalacznik.Location = new System.Drawing.Point(10, 190);
            this.btnDodajZalacznik.Size = new System.Drawing.Size(90, 28);
            this.btnDodajZalacznik.Text = "Dodaj...";
            this.btnDodajZalacznik.UseVisualStyleBackColor = true;
            this.btnDodajZalacznik.Click += new System.EventHandler(this.btnDodajZalacznik_Click);
            //
            // btnUsunZalacznik
            //
            this.btnUsunZalacznik.Location = new System.Drawing.Point(110, 190);
            this.btnUsunZalacznik.Size = new System.Drawing.Size(90, 28);
            this.btnUsunZalacznik.Text = "Usuń";
            this.btnUsunZalacznik.UseVisualStyleBackColor = true;
            this.btnUsunZalacznik.Click += new System.EventHandler(this.btnUsunZalacznik_Click);
            //
            // btnWyslij
            //
            this.btnWyslij.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold);
            this.btnWyslij.Location = new System.Drawing.Point(388, 260);
            this.btnWyslij.Size = new System.Drawing.Size(210, 40);
            this.btnWyslij.Text = "Wyślij e-mail";
            this.btnWyslij.UseVisualStyleBackColor = true;
            this.btnWyslij.Click += new System.EventHandler(this.btnWyslij_Click);
            //
            // Form1
            //
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(614, 430);
            this.Controls.Add(this.grpSmtp);
            this.Controls.Add(this.grpWiadomosc);
            this.Controls.Add(this.grpZalaczniki);
            this.Controls.Add(this.btnWyslij);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Klient e-mail";
            this.grpSmtp.ResumeLayout(false);
            this.grpSmtp.PerformLayout();
            this.grpWiadomosc.ResumeLayout(false);
            this.grpWiadomosc.PerformLayout();
            this.grpZalaczniki.ResumeLayout(false);
            this.ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.GroupBox grpSmtp;
        private System.Windows.Forms.TextBox txtSmtp;
        private System.Windows.Forms.TextBox txtPort;
        private System.Windows.Forms.TextBox txtLogin;
        private System.Windows.Forms.TextBox txtHaslo;
        private System.Windows.Forms.Label lblSmtp;
        private System.Windows.Forms.Label lblPort;
        private System.Windows.Forms.Label lblLogin;
        private System.Windows.Forms.Label lblHaslo;
        private System.Windows.Forms.CheckBox chkSsl;
        private System.Windows.Forms.GroupBox grpWiadomosc;
        private System.Windows.Forms.TextBox txtDo;
        private System.Windows.Forms.TextBox txtTemat;
        private System.Windows.Forms.TextBox txtTresc;
        private System.Windows.Forms.Label lblDo;
        private System.Windows.Forms.Label lblTemat;
        private System.Windows.Forms.Label lblTresc;
        private System.Windows.Forms.CheckBox chkHtml;
        private System.Windows.Forms.GroupBox grpZalaczniki;
        private System.Windows.Forms.ListBox lstZalaczniki;
        private System.Windows.Forms.Button btnDodajZalacznik;
        private System.Windows.Forms.Button btnUsunZalacznik;
        private System.Windows.Forms.Button btnWyslij;
    }
}
