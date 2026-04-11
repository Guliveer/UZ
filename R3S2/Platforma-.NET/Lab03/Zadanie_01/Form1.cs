using System;
using System.Windows.Forms;

namespace Zadanie_01
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnOblicz_Click(object sender, EventArgs e)
        {
            DateTime data1 = dtpData1.Value;
            DateTime data2 = dtpData2.Value;

            TimeSpan roznica = data2 - data1;

            if (roznica.TotalSeconds < 0)
                roznica = roznica.Negate();

            lblDni.Text = $"Dni: {(int)roznica.TotalDays}";
            lblGodziny.Text = $"Godziny: {(int)roznica.TotalHours}";
            lblMinuty.Text = $"Minuty: {(int)roznica.TotalMinutes}";
            lblSekundy.Text = $"Sekundy: {(int)roznica.TotalSeconds}";

            lblSzczegoly.Text = $"Dokładnie: {roznica.Days} dni, {roznica.Hours} godz, "
                              + $"{roznica.Minutes} min, {roznica.Seconds} sek";
        }
    }
}
