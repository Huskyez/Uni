namespace client
{
    partial class MainWindowForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.buttonLogOut = new System.Windows.Forms.Button();
            this.dataGridViewZboruri = new System.Windows.Forms.DataGridView();
            this.dataGridViewCautari = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxDestinatie = new System.Windows.Forms.TextBox();
            this.buttonCautare = new System.Windows.Forms.Button();
            this.textBoxOra = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.textBoxNumeClient = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.textBoxAdresa = new System.Windows.Forms.TextBox();
            this.textBoxTuristi = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.textBoxLocuri = new System.Windows.Forms.TextBox();
            this.buttonCumpara = new System.Windows.Forms.Button();
            this.textBoxData = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewZboruri)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCautari)).BeginInit();
            this.SuspendLayout();
            // 
            // buttonLogOut
            // 
            this.buttonLogOut.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonLogOut.Location = new System.Drawing.Point(862, 507);
            this.buttonLogOut.Name = "buttonLogOut";
            this.buttonLogOut.Size = new System.Drawing.Size(129, 33);
            this.buttonLogOut.TabIndex = 0;
            this.buttonLogOut.Text = "LOG OUT";
            this.buttonLogOut.UseVisualStyleBackColor = true;
            this.buttonLogOut.Click += new System.EventHandler(this.LogOut_Click);
            // 
            // dataGridViewZboruri
            // 
            this.dataGridViewZboruri.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dataGridViewZboruri.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewZboruri.Location = new System.Drawing.Point(26, 12);
            this.dataGridViewZboruri.Name = "dataGridViewZboruri";
            this.dataGridViewZboruri.RowTemplate.Height = 24;
            this.dataGridViewZboruri.Size = new System.Drawing.Size(640, 248);
            this.dataGridViewZboruri.TabIndex = 1;
            this.dataGridViewZboruri.RowHeaderMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.incarcareData);
            // 
            // dataGridViewCautari
            // 
            this.dataGridViewCautari.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewCautari.Location = new System.Drawing.Point(26, 305);
            this.dataGridViewCautari.Name = "dataGridViewCautari";
            this.dataGridViewCautari.RowTemplate.Height = 24;
            this.dataGridViewCautari.Size = new System.Drawing.Size(327, 235);
            this.dataGridViewCautari.TabIndex = 2;
            this.dataGridViewCautari.RowHeaderMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.incarcareAttibute);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label1.Location = new System.Drawing.Point(392, 340);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(85, 20);
            this.label1.TabIndex = 3;
            this.label1.Text = "Destinatia";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label2.Location = new System.Drawing.Point(392, 396);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(104, 20);
            this.label2.TabIndex = 4;
            this.label2.Text = "Data plecarii";
            // 
            // textBoxDestinatie
            // 
            this.textBoxDestinatie.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.textBoxDestinatie.Location = new System.Drawing.Point(507, 337);
            this.textBoxDestinatie.Name = "textBoxDestinatie";
            this.textBoxDestinatie.Size = new System.Drawing.Size(159, 26);
            this.textBoxDestinatie.TabIndex = 5;
            // 
            // buttonCautare
            // 
            this.buttonCautare.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.buttonCautare.Location = new System.Drawing.Point(507, 507);
            this.buttonCautare.Name = "buttonCautare";
            this.buttonCautare.Size = new System.Drawing.Size(159, 33);
            this.buttonCautare.TabIndex = 7;
            this.buttonCautare.Text = "Cauta";
            this.buttonCautare.UseVisualStyleBackColor = true;
            this.buttonCautare.Click += new System.EventHandler(this.buttonCautare_Click);
            // 
            // textBoxOra
            // 
            this.textBoxOra.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.textBoxOra.Location = new System.Drawing.Point(799, 12);
            this.textBoxOra.Name = "textBoxOra";
            this.textBoxOra.Size = new System.Drawing.Size(192, 26);
            this.textBoxOra.TabIndex = 9;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label3.Location = new System.Drawing.Point(689, 18);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(37, 20);
            this.label3.TabIndex = 10;
            this.label3.Text = "Ora";
            // 
            // textBoxNumeClient
            // 
            this.textBoxNumeClient.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.textBoxNumeClient.Location = new System.Drawing.Point(799, 68);
            this.textBoxNumeClient.Name = "textBoxNumeClient";
            this.textBoxNumeClient.Size = new System.Drawing.Size(192, 26);
            this.textBoxNumeClient.TabIndex = 11;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label4.Location = new System.Drawing.Point(689, 70);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(98, 20);
            this.label4.TabIndex = 12;
            this.label4.Text = "Nume client";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label5.Location = new System.Drawing.Point(689, 182);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(56, 20);
            this.label5.TabIndex = 13;
            this.label5.Text = "Turisti";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label6.Location = new System.Drawing.Point(689, 134);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(62, 20);
            this.label6.TabIndex = 14;
            this.label6.Text = "Adresa";
            // 
            // textBoxAdresa
            // 
            this.textBoxAdresa.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.textBoxAdresa.Location = new System.Drawing.Point(799, 131);
            this.textBoxAdresa.Name = "textBoxAdresa";
            this.textBoxAdresa.Size = new System.Drawing.Size(192, 26);
            this.textBoxAdresa.TabIndex = 15;
            // 
            // textBoxTuristi
            // 
            this.textBoxTuristi.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.textBoxTuristi.Location = new System.Drawing.Point(799, 179);
            this.textBoxTuristi.Name = "textBoxTuristi";
            this.textBoxTuristi.Size = new System.Drawing.Size(192, 26);
            this.textBoxTuristi.TabIndex = 16;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label7.Location = new System.Drawing.Point(682, 237);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(105, 20);
            this.label7.TabIndex = 17;
            this.label7.Text = "Numar locuri";
            // 
            // textBoxLocuri
            // 
            this.textBoxLocuri.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.textBoxLocuri.Location = new System.Drawing.Point(799, 234);
            this.textBoxLocuri.Name = "textBoxLocuri";
            this.textBoxLocuri.Size = new System.Drawing.Size(192, 26);
            this.textBoxLocuri.TabIndex = 18;
            // 
            // buttonCumpara
            // 
            this.buttonCumpara.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.buttonCumpara.Location = new System.Drawing.Point(693, 507);
            this.buttonCumpara.Name = "buttonCumpara";
            this.buttonCumpara.Size = new System.Drawing.Size(142, 33);
            this.buttonCumpara.TabIndex = 19;
            this.buttonCumpara.Text = "Cumpara";
            this.buttonCumpara.UseVisualStyleBackColor = true;
            this.buttonCumpara.Click += new System.EventHandler(this.buttonCumpara_Click);
            // 
            // textBoxData
            // 
            this.textBoxData.Location = new System.Drawing.Point(507, 396);
            this.textBoxData.Name = "textBoxData";
            this.textBoxData.Size = new System.Drawing.Size(159, 22);
            this.textBoxData.TabIndex = 21;
            // 
            // MainWindowForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1032, 561);
            this.Controls.Add(this.textBoxData);
            this.Controls.Add(this.buttonCumpara);
            this.Controls.Add(this.textBoxLocuri);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.textBoxTuristi);
            this.Controls.Add(this.textBoxAdresa);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.textBoxNumeClient);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.textBoxOra);
            this.Controls.Add(this.buttonCautare);
            this.Controls.Add(this.textBoxDestinatie);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dataGridViewCautari);
            this.Controls.Add(this.dataGridViewZboruri);
            this.Controls.Add(this.buttonLogOut);
            this.Name = "MainWindowForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "MainWindowForm";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewZboruri)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCautari)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonLogOut;
        private System.Windows.Forms.DataGridView dataGridViewZboruri;
        private System.Windows.Forms.DataGridView dataGridViewCautari;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxDestinatie;
        private System.Windows.Forms.Button buttonCautare;
        private System.Windows.Forms.TextBox textBoxOra;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox textBoxNumeClient;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox textBoxAdresa;
        private System.Windows.Forms.TextBox textBoxTuristi;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox textBoxLocuri;
        private System.Windows.Forms.Button buttonCumpara;
        private System.Windows.Forms.TextBox textBoxData;
    }
}