using System;
using System.Windows.Forms;

namespace Lab07
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnDangKy_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Đăng ký thành công!");
        }

        private void btnDangNhap_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Chuyển sang trang đăng nhập");
        }
    }
}