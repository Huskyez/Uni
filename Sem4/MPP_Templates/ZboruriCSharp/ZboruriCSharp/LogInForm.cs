/*using System;
using System.Windows.Forms;
using ZboruriCSharp.service;

namespace ZboruriCSharp
{
    public partial class LogInForm : Form
    {
        private Service service;
        private MainWindowForm main;
        public LogInForm()
        {
            InitializeComponent();
        }
        internal void SetService(Service service)
        {
            this.service = service;
        }
        internal void setMain(MainWindowForm mainWindowForm)
        {
            this.main = mainWindowForm;
        }
        private void LogIn_Click(object sender, EventArgs e)
        {
            String user = textBoxUser.Text;
            String pass = textBoxPassword.Text;
            if (service.LogIn(new Angajat(user, pass)) == true)
            {
                this.Hide();
                textBoxUser.Clear();
                textBoxPassword.Clear();
                main.Show();
            }
            else
            {
                MessageBox.Show("Invalid credentials!");
                textBoxUser.Clear();
                textBoxPassword.Clear();
            }
        }

        private void textBoxUser_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
*/