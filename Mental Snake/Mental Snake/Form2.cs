using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Mental_Snake
{
    public partial class Form2 : Form
    {
        int x;
        int y;
        int a;
        int b;
        public Form2()
        {
            InitializeComponent();

            x = 3;
            y = 3;
            this.Board.CellPaint += new TableLayoutCellPaintEventHandler(Board_CellPaint);

        }

        public void Board_CellPaint(object sender, TableLayoutCellPaintEventArgs e)
        {
            x = e.Column;
            y = e.Row;
            Graphics g = e.Graphics;
            Rectangle r = e.CellBounds;
            g.FillRectangle(Brushes.Blue, r);
        }
            

        private void button1_Click(object sender, EventArgs e)
        {
            if (y == 5)
            {
                System.Windows.Forms.MessageBox.Show("CRASH!!!!!!");
            }
            else
            {
                y = y + 1;
               
            }
        }
        private void button2_Click(object sender, EventArgs e)
        {
            if (x == 1)
            {
                System.Windows.Forms.MessageBox.Show("CRASH!!!!!!");
            }
            else
            {
                x = x - 1;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (x == 5)
            {
                System.Windows.Forms.MessageBox.Show("CRASH!!!!!!");
            }
            else
            {
                x = x + 1;
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (y == 1)
            {
                System.Windows.Forms.MessageBox.Show("CRASH!!!!!!");
            }
            else
            {
                y = y - 1;
            }
        }

        
    }
}
