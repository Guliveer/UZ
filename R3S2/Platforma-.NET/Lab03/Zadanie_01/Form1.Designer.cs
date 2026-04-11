namespace Zadanie_01
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
            this.dtpData1 = new System.Windows.Forms.DateTimePicker();
            this.dtpData2 = new System.Windows.Forms.DateTimePicker();
            this.btnOblicz = new System.Windows.Forms.Button();
            this.lblData1 = new System.Windows.Forms.Label();
            this.lblData2 = new System.Windows.Forms.Label();
            this.lblDni = new System.Windows.Forms.Label();
            this.lblGodziny = new System.Windows.Forms.Label();
            this.lblMinuty = new System.Windows.Forms.Label();
            this.lblSekundy = new System.Windows.Forms.Label();
            this.lblSzczegoly = new System.Windows.Forms.Label();
            this.groupWynik = new System.Windows.Forms.GroupBox();
            this.groupWynik.SuspendLayout();
            this.SuspendLayout();
            //
            // lblData1
            //
            this.lblData1.AutoSize = true;
            this.lblData1.Location = new System.Drawing.Point(20, 25);
            this.lblData1.Name = "lblData1";
            this.lblData1.Size = new System.Drawing.Size(80, 13);
            this.lblData1.Text = "Data początkowa:";
            //
            // dtpData1
            //
            this.dtpData1.CustomFormat = "dd.MM.yyyy HH:mm:ss";
            this.dtpData1.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dtpData1.Location = new System.Drawing.Point(140, 20);
            this.dtpData1.Name = "dtpData1";
            this.dtpData1.Size = new System.Drawing.Size(200, 20);
            //
            // lblData2
            //
            this.lblData2.AutoSize = true;
            this.lblData2.Location = new System.Drawing.Point(20, 60);
            this.lblData2.Name = "lblData2";
            this.lblData2.Size = new System.Drawing.Size(80, 13);
            this.lblData2.Text = "Data końcowa:";
            //
            // dtpData2
            //
            this.dtpData2.CustomFormat = "dd.MM.yyyy HH:mm:ss";
            this.dtpData2.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dtpData2.Location = new System.Drawing.Point(140, 55);
            this.dtpData2.Name = "dtpData2";
            this.dtpData2.Size = new System.Drawing.Size(200, 20);
            //
            // btnOblicz
            //
            this.btnOblicz.Location = new System.Drawing.Point(140, 90);
            this.btnOblicz.Name = "btnOblicz";
            this.btnOblicz.Size = new System.Drawing.Size(200, 30);
            this.btnOblicz.Text = "Oblicz różnicę";
            this.btnOblicz.UseVisualStyleBackColor = true;
            this.btnOblicz.Click += new System.EventHandler(this.btnOblicz_Click);
            //
            // groupWynik
            //
            this.groupWynik.Controls.Add(this.lblDni);
            this.groupWynik.Controls.Add(this.lblGodziny);
            this.groupWynik.Controls.Add(this.lblMinuty);
            this.groupWynik.Controls.Add(this.lblSekundy);
            this.groupWynik.Controls.Add(this.lblSzczegoly);
            this.groupWynik.Location = new System.Drawing.Point(20, 135);
            this.groupWynik.Name = "groupWynik";
            this.groupWynik.Size = new System.Drawing.Size(340, 160);
            this.groupWynik.TabStop = false;
            this.groupWynik.Text = "Wynik";
            //
            // lblDni
            //
            this.lblDni.AutoSize = true;
            this.lblDni.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.lblDni.Location = new System.Drawing.Point(15, 25);
            this.lblDni.Name = "lblDni";
            this.lblDni.Size = new System.Drawing.Size(40, 17);
            this.lblDni.Text = "Dni: -";
            //
            // lblGodziny
            //
            this.lblGodziny.AutoSize = true;
            this.lblGodziny.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.lblGodziny.Location = new System.Drawing.Point(15, 50);
            this.lblGodziny.Name = "lblGodziny";
            this.lblGodziny.Size = new System.Drawing.Size(60, 17);
            this.lblGodziny.Text = "Godziny: -";
            //
            // lblMinuty
            //
            this.lblMinuty.AutoSize = true;
            this.lblMinuty.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.lblMinuty.Location = new System.Drawing.Point(15, 75);
            this.lblMinuty.Name = "lblMinuty";
            this.lblMinuty.Size = new System.Drawing.Size(55, 17);
            this.lblMinuty.Text = "Minuty: -";
            //
            // lblSekundy
            //
            this.lblSekundy.AutoSize = true;
            this.lblSekundy.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.lblSekundy.Location = new System.Drawing.Point(15, 100);
            this.lblSekundy.Name = "lblSekundy";
            this.lblSekundy.Size = new System.Drawing.Size(65, 17);
            this.lblSekundy.Text = "Sekundy: -";
            //
            // lblSzczegoly
            //
            this.lblSzczegoly.AutoSize = true;
            this.lblSzczegoly.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Italic);
            this.lblSzczegoly.Location = new System.Drawing.Point(15, 130);
            this.lblSzczegoly.Name = "lblSzczegoly";
            this.lblSzczegoly.Size = new System.Drawing.Size(60, 15);
            this.lblSzczegoly.Text = "";
            //
            // Form1
            //
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(384, 311);
            this.Controls.Add(this.lblData1);
            this.Controls.Add(this.dtpData1);
            this.Controls.Add(this.lblData2);
            this.Controls.Add(this.dtpData2);
            this.Controls.Add(this.btnOblicz);
            this.Controls.Add(this.groupWynik);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Kalkulator różnicy dat";
            this.groupWynik.ResumeLayout(false);
            this.groupWynik.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion

        private System.Windows.Forms.DateTimePicker dtpData1;
        private System.Windows.Forms.DateTimePicker dtpData2;
        private System.Windows.Forms.Button btnOblicz;
        private System.Windows.Forms.Label lblData1;
        private System.Windows.Forms.Label lblData2;
        private System.Windows.Forms.Label lblDni;
        private System.Windows.Forms.Label lblGodziny;
        private System.Windows.Forms.Label lblMinuty;
        private System.Windows.Forms.Label lblSekundy;
        private System.Windows.Forms.Label lblSzczegoly;
        private System.Windows.Forms.GroupBox groupWynik;
    }
}
