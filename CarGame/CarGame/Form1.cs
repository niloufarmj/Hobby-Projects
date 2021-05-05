using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CarGame
{
    public partial class Form1 : Form
    {

        int loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8; //obstacle locations
        int coin_loc1, coin_loc2, coin_loc3, coin_loc4, coin_loc5, coin_loc6, coin_loc7, coin_loc8; //coin locations
        int coin_num = 0; //number of gathered coins

        //this variable controls game speed
        //as you gain more coins the speed game will raise so the game gets harder
        int ctr = 0; 

        int gameSpeed = 7;
        Random r = new Random();

        //function to move car
        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {

            //on left or right key of the keyboead the car would move in respect to game speed
            if (e.KeyCode == Keys.Left)
            {
                if (carPic.Left > 0)
                    carPic.Left -= (gameSpeed + gameSpeed / 3);
            }
            else if (e.KeyCode == Keys.Right)
            {
                if (carPic.Right < 425)
                    carPic.Left += (gameSpeed + gameSpeed / 3);
            }
            
        }

        public Form1()
        {
            InitializeComponent();
        }
        
        //this function will control time to move objects
        private void timer1_Tick(object sender, EventArgs e)
        {
            moveBackGround(gameSpeed);
            moveObs(gameSpeed);
            moveCoins(gameSpeed);
            

            if (carPic.Bounds.IntersectsWith(coin1.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin1.Top = 871;
                ctr++;
            }

            if (carPic.Bounds.IntersectsWith(coin2.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin2.Top = 871;
                ctr++;
            }

            if (carPic.Bounds.IntersectsWith(coin3.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin3.Top = 871;
                ctr++;
            }

            if (carPic.Bounds.IntersectsWith(coin4.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin4.Top = 871;
                ctr++;
            }

            if (carPic.Bounds.IntersectsWith(coin5.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin5.Top = 871;
                ctr++;
            }

            if (carPic.Bounds.IntersectsWith(coin6.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin6.Top = 871;
                ctr++;
            }

            if (carPic.Bounds.IntersectsWith(coin7.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin7.Top = 871;
                ctr++;
            }

            if (carPic.Bounds.IntersectsWith(coin8.Bounds))
            {
                coin_num++;
                coin_label.Text = coin_num.ToString();
                coin8.Top = 871;
                ctr++;
            }

            if (ctr == 5)
            {
                gameSpeed++;
                ctr = 0;
            }


            if (carPic.Bounds.IntersectsWith(obs1.Bounds) || carPic.Bounds.IntersectsWith(obs5.Bounds) ||
                carPic.Bounds.IntersectsWith(obs2.Bounds) || carPic.Bounds.IntersectsWith(obs6.Bounds) ||
                carPic.Bounds.IntersectsWith(obs3.Bounds) || carPic.Bounds.IntersectsWith(obs7.Bounds) ||
                carPic.Bounds.IntersectsWith(obs4.Bounds) || carPic.Bounds.IntersectsWith(obs8.Bounds))
            {
                gameOverPic.Visible = true;
                gameOverLabel.Visible = true;
                gameSpeed = 0;
            }
            

        }

        void moveBackGround(int speed)
        {
           
            if (line1.Top >= 870)
            {
                line1.Top = 100;
            }
            else
            {
                line1.Top += speed;
            }

            if (line2.Top >= 870)
            {
                line2.Top = 100;
            }
            else
            {
                line2.Top += speed;
            }

            if (line3.Top >= 870)
            {
                line3.Top = 100;
            }
            else
            {
                line3.Top += speed;
            }

            if (line4.Top >= 870)
            {
                line4.Top = 100;
            }
            else
            {
                line4.Top += speed;
            }

            if (line9.Top >= 870)
                line9.Top = 100;
            else
                line9.Top += speed;

            if (line5.Top >= 870)
                line5.Top = 100;
            else
                line5.Top += speed;

            if (line6.Top >= 870)
                line6.Top = 100;
            else
                line6.Top += speed;

            if (line7.Top >= 870)
                line7.Top = 100;
            else
                line7.Top += speed;

            if (line8.Top >= 870)
                line8.Top = 100;
            else
                line8.Top += speed;

            if (line10.Top >= 870)
                line10.Top = 100;
            else
                line10.Top += speed;

        }

        void moveObs(int speed)
        {
            loc1 = r.Next(-850, -750);
            loc2 = r.Next(-650, -450);
            loc3 = r.Next(-850, -750);
            loc4 = r.Next(-650, -450);
            loc5 = r.Next(-1300, -1100);
            loc6 = r.Next(-250, -100);
            loc7 = r.Next(-1300, -1100);
            loc8 = r.Next(-250, -100);
            if (obs1.Top >= 870)
            {
                /*while (Math.Abs(loc1 - loc2) < 200 || Math.Abs(loc1 - loc5) < 200 || Math.Abs(loc1 - loc6) < 200)
                    loc1 = r.Next(-600, -100);*/
                if (Math.Abs(loc1 - obs5.Top) < 200)
                    loc1 -= 400;
                if (Math.Abs(loc1 - obs3.Top) < 200)
                    loc1 -= 400;
                if (Math.Abs(loc1 - obs6.Top) < 200)
                    loc1 -= 400;
                obs1.Top = loc1;
            }
            else
                obs1.Top += speed;

            if (obs2.Top >= 870)
            {
                /*while (Math.Abs(loc2 - loc1) < 200 || Math.Abs(loc2 - loc5) < 200 || Math.Abs(loc2 - loc6) < 200 || Math.Abs(loc2 - loc7) < 200 || Math.Abs(loc2 - loc3) < 200)
                    loc2 = r.Next(-600, -100);
                obs2.Top = loc2;*/
                if (Math.Abs(loc2 - obs6.Top) < 200)
                    loc2 -= 400;
                if (Math.Abs(loc2 - obs3.Top) < 200)
                    loc2 -= 400;
                if (Math.Abs(loc2 - obs6.Top) < 200)
                    loc2 -= 400;
                if (Math.Abs(loc2 - obs7.Top) < 200)
                    loc2 -= 400;
                obs2.Top = loc2;
            }
            else
                obs2.Top += speed;

            if (obs3.Top >= 870)
            {
                /*while (Math.Abs(loc3 - loc2) < 200 || Math.Abs(loc3 - loc7) < 200 || Math.Abs(loc3 - loc6) < 200 || Math.Abs(loc3 - loc4) < 200 || Math.Abs(loc3 - loc8) < 200)
                    loc3 = r.Next(-600, -100);
                obs3.Top = loc3;*/
                if (Math.Abs(loc3 - obs7.Top) < 200)
                    loc3 -= 400;
                if (Math.Abs(loc3 - obs4.Top) < 200)
                    loc3 -= 400;
                if (Math.Abs(loc3 - obs6.Top) < 200)
                    loc3 -= 400;
                if (Math.Abs(loc3 - obs8.Top) < 200)
                    loc3 -= 400;
                obs3.Top = loc3;
            }
            else
                obs3.Top += speed;

            if (obs4.Top > 870)
            {
                /*while (Math.Abs(loc4 - loc3) < 200 || Math.Abs(loc4 - loc7) < 200 || Math.Abs(loc4 - loc8) < 200)
                    loc4 = r.Next(-600, -100);*/
                if (Math.Abs(loc4 - obs8.Top) < 200)
                    loc4 -= 400;
                if (Math.Abs(loc4 - obs7.Top) < 200)
                    loc4 -= 400;
                if (Math.Abs(loc4 - obs3.Top) < 200)
                    loc4 -= 400;
                obs4.Top = loc4;
            }
            else
                obs4.Top += speed;

            if (obs5.Top > 870)
            {
                /*while (Math.Abs(loc5 - loc1) < 200 || Math.Abs(loc5 - loc2) < 200 || Math.Abs(loc5 - loc6) < 200)
                    loc5 = r.Next(-600, -100);*/
                if (Math.Abs(loc5 - obs1.Top) < 200)
                    loc5 -= 400;
                if (Math.Abs(loc5 - obs3.Top) < 200)
                    loc5 -= 400;
                if (Math.Abs(loc5 - obs6.Top) < 200)
                    loc5 -= 400;
                   obs5.Top = loc5;
            }
            else
                obs5.Top += speed;

            if (obs6.Top > 870)
            {
                /*while (Math.Abs(loc6 - loc5) < 200 || Math.Abs(loc6 - loc1) < 200 || Math.Abs(loc6 - loc2) < 200 || Math.Abs(loc6 - loc3) < 200 || Math.Abs(loc6 - loc7) < 200)
                    loc6 = r.Next(-600, -100);*/
                if (Math.Abs(loc6 - obs2.Top) < 200)
                    loc6 -= 400;
                if (Math.Abs(loc6 - obs1.Top) < 200)
                    loc6 -= 400;
                if (Math.Abs(loc6 - obs5.Top) < 200)
                    loc6 -= 400;
                if (Math.Abs(loc6 - obs3.Top) < 200)
                    loc6 -= 400;
                if (Math.Abs(loc6 - obs7.Top) < 200)
                    loc6 -= 400;
                obs6.Top = loc6;
            }
            else
                obs6.Top += speed;

            if (obs7.Top > 870)
            {
                /*while (Math.Abs(loc7 - loc2) < 200 || Math.Abs(loc7 - loc6) < 200 || Math.Abs(loc7 - loc3) < 200 || Math.Abs(loc7 - loc4) < 200 || Math.Abs(loc7 - loc8) < 200)
                    loc7 = r.Next(-600, -100);*/
                if (Math.Abs(loc7 - obs3.Top) < 200)
                    loc7 -= 400;
                if (Math.Abs(loc7 - obs4.Top) < 200)
                    loc7 -= 400;
                if (Math.Abs(loc7 - obs2.Top) < 200)
                    loc7 -= 400;
                if (Math.Abs(loc7 - obs6.Top) < 200)
                    loc7 -= 400;
                if (Math.Abs(loc7 - obs8.Top) < 200)
                    loc7 -= 400;
                obs7.Top = loc7;
            }
            else
                obs7.Top += speed;

            if (obs8.Top > 870)
            {
                /*while (Math.Abs(loc8 - loc3) < 200 || Math.Abs(loc8 - loc4) < 200 || Math.Abs(loc8 - loc7) < 200)
                    loc8 = r.Next(-600, -100);*/
                if (Math.Abs(loc8 - obs4.Top) < 200)
                    loc8 -= 400;
                if (Math.Abs(loc8 - obs3.Top) < 200)
                    loc8 -= 400;
                if (Math.Abs(loc8 - obs7.Top) < 200)
                    loc8 -= 400;
                obs8.Top = loc8;
            }
            else
                obs8.Top += speed;
        }

        void moveCoins(int speed)
        {
            coin_loc1 = r.Next(-850, -650);
            coin_loc2 = r.Next(-550, -350);
            coin_loc3 = r.Next(-850, -650);
            coin_loc4 = r.Next(-550, -350);
            coin_loc5 = r.Next(-1000, -900);
            coin_loc6 = r.Next(-250, -100);
            coin_loc7 = r.Next(-1000, -900);
            coin_loc8 = r.Next(-250, -100);

            if (coin1.Top >= 870)
            {

                coin1.Top = coin_loc1;
            }
            else
                coin1.Top += speed;

            if (coin2.Top >= 870)
            {
                coin2.Top = coin_loc2;
            }
            else
                coin2.Top += speed;

            if (coin3.Top >= 870)
            {

                coin3.Top = coin_loc3;
            }
            else
                coin3.Top += speed;

            if (coin4.Top > 870)
            {

                coin4.Top = coin_loc4;
            }
            else
                coin4.Top += speed;

            if (coin5.Top > 870)
            {

                coin5.Top = coin_loc5;
            }
            else
                coin5.Top += speed;

            if (coin6.Top > 870)
            {

                coin6.Top = coin_loc6;
            }
            else
                coin6.Top += speed;

            if (coin7.Top > 870)
            {

                coin7.Top = coin_loc7;
            }
            else
                coin7.Top += speed;

            if (coin8.Top > 870)
            {

                coin8.Top = coin_loc8;
            }
            else
                coin8.Top += speed;
        }

        private void close(object sender, EventArgs e)
        {
            this.Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            background.Controls.Add(carPic);
            background.Controls.Add(obs1);
            background.Controls.Add(obs2);
            background.Controls.Add(obs3);
            background.Controls.Add(obs4);
            background.Controls.Add(obs5);
            background.Controls.Add(obs6);
            background.Controls.Add(obs7);
            background.Controls.Add(obs8);

            background.Controls.Add(coin1);
            background.Controls.Add(coin2);
            background.Controls.Add(coin3);
            background.Controls.Add(coin4);
            background.Controls.Add(coin5);
            background.Controls.Add(coin6);
            background.Controls.Add(coin7);
            background.Controls.Add(coin8);

            background.Controls.Add(line1);
            background.Controls.Add(line2);
            background.Controls.Add(line3);
            background.Controls.Add(line4);
            background.Controls.Add(line5);
            background.Controls.Add(line6);
            background.Controls.Add(line7);
            background.Controls.Add(line8);
            background.Controls.Add(line9);
            background.Controls.Add(line10);
            //TransparetBackground(car);

            carPic.BackColor = Color.Transparent;

            obs1.BackColor = Color.Transparent;
            obs2.BackColor = Color.Transparent;
            obs3.BackColor = Color.Transparent;
            obs4.BackColor = Color.Transparent;
            obs5.BackColor = Color.Transparent;
            obs6.BackColor = Color.Transparent;
            obs7.BackColor = Color.Transparent;
            obs8.BackColor = Color.Transparent;

            coin1.BackColor = Color.Transparent;
            coin2.BackColor = Color.Transparent;
            coin3.BackColor = Color.Transparent;
            coin4.BackColor = Color.Transparent;
            coin5.BackColor = Color.Transparent;
            coin6.BackColor = Color.Transparent;
            coin7.BackColor = Color.Transparent;
            coin8.BackColor = Color.Transparent;

            init_locations();

            gameOverPic.Visible = false;
            gameOverLabel.Visible = false;
        }

        public void init_locations()
        {
            
            loc1 = r.Next(-850, -650);
            loc2 = r.Next(-550, -350);
            loc3 = r.Next(-850, -650);
            loc4 = r.Next(-550, -350);
            loc5 = r.Next(-1000, -900);
            loc6 = r.Next(-250, -100);
            loc7 = r.Next(-1000, -900);
            loc8 = r.Next(-250, -100);

            coin_loc1 = r.Next(-850, -650);
            coin_loc2 = r.Next(-550, -350);
            coin_loc3 = r.Next(-850, -650);
            coin_loc4 = r.Next(-550, -350);
            coin_loc5 = r.Next(-1000, -900);
            coin_loc6 = r.Next(-250, -100);
            coin_loc7 = r.Next(-1000, -900);
            coin_loc8 = r.Next(-250, -100);


            obs1.Top = loc1;
            obs2.Top = loc2;
            obs3.Top = loc3;
            obs4.Top = loc4;
            obs5.Top = loc5;
            obs6.Top = loc6;
            obs7.Top = loc7;
            obs8.Top = loc8;

            coin1.Top = coin_loc1;
            coin2.Top = coin_loc2;
            coin3.Top = coin_loc3;
            coin4.Top = coin_loc4;
            coin5.Top = coin_loc5;
            coin6.Top = coin_loc6;
            coin7.Top = coin_loc7;
            coin8.Top = coin_loc8;

            line1.Top += 200;
            line2.Top += 200;
            line3.Top += 200;
            line4.Top += 200;
            line5.Top += 200;
            line6.Top += 200;
            line7.Top += 200;
            line8.Top += 200;

            line9.Top = 100;
            line10.Top = 100;
            carPic.Top = 720;
        }
    }
}
