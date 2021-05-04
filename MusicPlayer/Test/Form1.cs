using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Test
{
    public partial class Form1 : Form
    {

        string[] paths, files;

        public Form1()
        {
            InitializeComponent();
        }

        private void selectSongBtn_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Multiselect = true;

            if (ofd.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                files = ofd.SafeFileNames;
                paths = ofd.FileNames;
                for (int i = 0; i < files.Length; i++)
                {
                    songListBox.Items.Add(files[i]);
                }
            }
        }

        private void songListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            mediaPlayer.URL = paths[songListBox.SelectedIndex];
        }

        private void closeBox_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
