/*using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ZboruriCSharp.service;

namespace ZboruriCSharp
{
    public partial class MainWindowForm : Form
    {
        Service service;
        LogInForm logInForm;
        public MainWindowForm()
        {
            InitializeComponent();
        }
        internal void SetService(Service service)
        {
            this.service = service;
            dataGridViewZboruri.DataSource = service.GetZboruri();
        }

        internal void SetLogIn(LogInForm logInForm)
        {
            this.logInForm = logInForm;
        }
        private void LogOut_Click(object sender, EventArgs e)
        {
            this.Hide();
            logInForm.Show();
        }

        private void buttonCautare_Click(object sender, EventArgs e)
        {
            String dest = textBoxDestinatie.Text;
            String data = textBoxData.Text;
            String data1 = data.Remove(10);
            String[] data2 = data1.Split('.');
            String dataCorecta = data2[2] + "-" + data2[1] + "-" + data2[0];
            dataGridViewCautari.DataSource = service.GetZboruriCautate(dest, dataCorecta);
        }
        private void incarcareAttibute(object sender, DataGridViewCellMouseEventArgs e)
        {
            textBoxOra.Text = dataGridViewCautari.CurrentRow.Cells["ora"].Value.ToString();
        }

        private void buttonCumpara_Click(object sender, EventArgs e)
        {
            String numeClient = textBoxNumeClient.Text;
            String adresaClient = textBoxAdresa.Text;
            String turisti = textBoxTuristi.Text;
            int locuri = Int32.Parse(textBoxLocuri.Text);
            String destinatie = textBoxDestinatie.Text;

            String data = textBoxData.Text;
            String data1 = data.Remove(10);
            String[] data2 = data1.Split('.');
            String dataCorecta = data2[2] + "-" + data2[1] + "-" + data2[0];
            String ora = textBoxOra.Text;
            service.CumparaBilet(numeClient, adresaClient, turisti, locuri, destinatie, dataCorecta, ora);
            dataGridViewZboruri.DataSource = service.GetZboruri();
        }

        private void incarcareData(object sender, DataGridViewCellMouseEventArgs e)
        {
            textBoxDestinatie.Text = dataGridViewZboruri.CurrentRow.Cells["Destinatie"].Value.ToString();
            textBoxData.Text = dataGridViewZboruri.CurrentRow.Cells["Plecare"].Value.ToString();
        }
    }

}
*/