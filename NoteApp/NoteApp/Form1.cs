using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace NoteApp
{
    public partial class Form1 : Form
    {

        DataTable table;
        public Form1()
        {
            InitializeComponent();
        }

        public void Form1_Load(object sender, EventArgs e)
        {
            table = new DataTable();
            table.Columns.Add("Title", typeof(string));
            table.Columns.Add("Messages", typeof(string));
            dataGridView1.DataSource = table;
            dataGridView1.Columns["Messages"].Visible = false;
            dataGridView1.Columns["Title"].Width = 246;
        }
        
        private void newBtn_Click(object sender, EventArgs e)
        {
            titleBox.Clear();
            messageBox.Clear();
        }

        private void saveBtn_Click(object sender, EventArgs e)
        {
            table.Rows.Add(titleBox.Text, messageBox.Text);
            titleBox.Clear();
            messageBox.Clear();
        }

        private void readBtn_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentCell.RowIndex > -1)
            {
                titleBox.Text = table.Rows[dataGridView1.CurrentCell.RowIndex].ItemArray[0].ToString();
                messageBox.Text = table.Rows[dataGridView1.CurrentCell.RowIndex].ItemArray[1].ToString();
            }
        }

        private void deleteBtn_Click(object sender, EventArgs e)
        {
            table.Rows[dataGridView1.CurrentCell.RowIndex].Delete();
        }
    }
}
