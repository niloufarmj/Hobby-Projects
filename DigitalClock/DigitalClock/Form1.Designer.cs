
namespace DigitalClock
{
    partial class Form1
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
            this.components = new System.ComponentModel.Container();
            this.hour_min = new System.Windows.Forms.Label();
            this.sec = new System.Windows.Forms.Label();
            this.date = new System.Windows.Forms.Label();
            this.day = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.timer = new System.Windows.Forms.Timer(this.components);
            this.SuspendLayout();
            // 
            // hour_min
            // 
            this.hour_min.AutoSize = true;
            this.hour_min.Font = new System.Drawing.Font("DS-Digital", 72F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.hour_min.ForeColor = System.Drawing.Color.GreenYellow;
            this.hour_min.Location = new System.Drawing.Point(135, 48);
            this.hour_min.Name = "hour_min";
            this.hour_min.Size = new System.Drawing.Size(312, 118);
            this.hour_min.TabIndex = 0;
            this.hour_min.Text = "22:22";
            // 
            // sec
            // 
            this.sec.AutoSize = true;
            this.sec.Font = new System.Drawing.Font("DS-Digital", 36F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.sec.ForeColor = System.Drawing.Color.GreenYellow;
            this.sec.Location = new System.Drawing.Point(453, 97);
            this.sec.Name = "sec";
            this.sec.Size = new System.Drawing.Size(83, 59);
            this.sec.TabIndex = 1;
            this.sec.Text = "22";
            // 
            // date
            // 
            this.date.AutoSize = true;
            this.date.Font = new System.Drawing.Font("DS-Digital", 36F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.date.ForeColor = System.Drawing.Color.GreenYellow;
            this.date.Location = new System.Drawing.Point(66, 217);
            this.date.Name = "date";
            this.date.Size = new System.Drawing.Size(272, 59);
            this.date.TabIndex = 2;
            this.date.Text = "MAY 6 2021";
            // 
            // day
            // 
            this.day.AutoSize = true;
            this.day.Font = new System.Drawing.Font("DS-Digital", 25.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.day.ForeColor = System.Drawing.Color.GreenYellow;
            this.day.Location = new System.Drawing.Point(465, 231);
            this.day.Name = "day";
            this.day.Size = new System.Drawing.Size(195, 43);
            this.day.TabIndex = 3;
            this.day.Text = "SATURDAY";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.ForeColor = System.Drawing.SystemColors.HotTrack;
            this.label6.Location = new System.Drawing.Point(549, 318);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(148, 17);
            this.label6.TabIndex = 5;
            this.label6.Text = "Developed By Nanami";
            // 
            // timer
            // 
            this.timer.Interval = 1000;
            this.timer.Tick += new System.EventHandler(this.timer_Tick);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Navy;
            this.ClientSize = new System.Drawing.Size(721, 360);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.day);
            this.Controls.Add(this.date);
            this.Controls.Add(this.sec);
            this.Controls.Add(this.hour_min);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label hour_min;
        private System.Windows.Forms.Label sec;
        private System.Windows.Forms.Label date;
        private System.Windows.Forms.Label day;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Timer timer;
    }
}

