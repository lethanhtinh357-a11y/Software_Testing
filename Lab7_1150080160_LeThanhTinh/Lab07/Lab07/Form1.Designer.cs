using System;
using System.Drawing;
using System.Windows.Forms;

namespace Lab07
{
    partial class Form1
    {
        private System.ComponentModel.IContainer components = null;

        private Panel mainPanel;
        private Button btnDangKy;
        private Button btnDangNhap;

        protected override void Dispose(bool disposing)
        {
            if (disposing && components != null)
                components.Dispose();
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.Text = "Đăng ký tài khoản";
            this.Size = new Size(700, 750);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.WhiteSmoke;

            // PANEL CÓ THANH CUỘN
            mainPanel = new Panel();
            mainPanel.Dock = DockStyle.Fill;
            mainPanel.AutoScroll = true;   // ✅ QUAN TRỌNG
            mainPanel.BackColor = Color.White;
            this.Controls.Add(mainPanel);

            int y = 20;

            Label title = new Label();
            title.Text = "ĐĂNG KÝ TÀI KHOẢN";
            title.Font = new Font("Segoe UI", 16, FontStyle.Bold);
            title.ForeColor = Color.DarkBlue;
            title.AutoSize = true;
            title.Location = new Point(200, y);
            mainPanel.Controls.Add(title);

            y += 60;

            AddField("Họ và tên (*)", ref y);
            AddField("Tên đăng nhập (*)", ref y);
            AddField("Email (*)", ref y);
            AddField("Số điện thoại (*)", ref y);
            AddField("Mật khẩu (*)", ref y, true);
            AddField("Xác nhận mật khẩu (*)", ref y, true);
            AddField("Ngày sinh (dd/MM/yyyy)", ref y);
            AddField("Mã giới thiệu", ref y);

            // ===== GIỚI TÍNH =====
            Label lblGT = new Label();
            lblGT.Text = "Giới tính";
            lblGT.Location = new Point(100, y);
            lblGT.AutoSize = true;
            mainPanel.Controls.Add(lblGT);

            y += 30;

            RadioButton radNam = new RadioButton();
            radNam.Text = "Nam";
            radNam.Location = new Point(120, y);
            mainPanel.Controls.Add(radNam);

            RadioButton radNu = new RadioButton();
            radNu.Text = "Nữ";
            radNu.Location = new Point(200, y);
            mainPanel.Controls.Add(radNu);

            RadioButton radKhong = new RadioButton();
            radKhong.Text = "Không tiết lộ";
            radKhong.Location = new Point(260, y);
            mainPanel.Controls.Add(radKhong);

            y += 50;

            CheckBox chk = new CheckBox();
            chk.Text = "Tôi đồng ý Điều khoản";
            chk.Location = new Point(100, y);
            mainPanel.Controls.Add(chk);

            y += 60;

            // ===== NÚT ĐĂNG KÝ =====
            btnDangKy = new Button();
            btnDangKy.Text = "Đăng ký";
            btnDangKy.Size = new Size(180, 45);
            btnDangKy.Location = new Point(150, y);
            btnDangKy.BackColor = Color.RoyalBlue;
            btnDangKy.ForeColor = Color.White;
            btnDangKy.FlatStyle = FlatStyle.Flat;
            btnDangKy.Click += btnDangKy_Click;
            mainPanel.Controls.Add(btnDangKy);

            // ===== NÚT ĐĂNG NHẬP =====
            btnDangNhap = new Button();
            btnDangNhap.Text = "Đăng nhập";
            btnDangNhap.Size = new Size(180, 45);
            btnDangNhap.Location = new Point(350, y);
            btnDangNhap.BackColor = Color.Gray;
            btnDangNhap.ForeColor = Color.White;
            btnDangNhap.FlatStyle = FlatStyle.Flat;
            btnDangNhap.Click += btnDangNhap_Click;
            mainPanel.Controls.Add(btnDangNhap);
        }

        private void AddField(string text, ref int y, bool password = false)
        {
            Label lbl = new Label();
            lbl.Text = text;
            lbl.Location = new Point(100, y);
            lbl.AutoSize = true;
            mainPanel.Controls.Add(lbl);

            y += 25;

            TextBox txt = new TextBox();
            txt.Location = new Point(100, y);
            txt.Width = 450;
            if (password)
                txt.UseSystemPasswordChar = true;

            mainPanel.Controls.Add(txt);

            y += 45;
        }
    }
}