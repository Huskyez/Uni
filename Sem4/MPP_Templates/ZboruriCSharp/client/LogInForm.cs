using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using model;
using services;

namespace client
{
    public partial class LogInForm : Form
    {
        public ClientController clientController;
        public int port;
        public MainWindowForm mainWindowForm;
        public LogInForm(ClientController clientController)
        {
            InitializeComponent();
            this.clientController = clientController;
        }
        internal void Set(MainWindowForm mainWindowForm, int port)
        {
            this.mainWindowForm = mainWindowForm;
            this.port = port;
        }
        private void LogIn_Click(object sender, EventArgs e)
        {
            String user = textBoxUser.Text;
            String pass = textBoxPassword.Text;
            try
            {
                Angajat angajat = new Angajat(user, pass);
                clientController.LogIn(angajat);
                clientController.server.addObserver(port);
                this.Hide();
                mainWindowForm.Show();
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, "Login error" + ex.Message);
                return;
            }
        }

        private void LogInForm_Load(object sender, EventArgs e)
        {
        }
    }
}
