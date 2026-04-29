using System.Drawing.Drawing2D;

namespace JoystickDemo;

public static class JoystickVisualizer
{
    public static void Draw(Graphics g, Rectangle bounds, InputSnapshot snap)
    {
        g.SmoothingMode = SmoothingMode.AntiAlias;
        g.Clear(Color.White);

        if (!snap.IsConnected)
        {
            using var f = new Font("Segoe UI", 14f, FontStyle.Bold);
            var sz = g.MeasureString("Brak podlaczonego urzadzenia", f);
            g.DrawString("Brak podlaczonego urzadzenia", f, Brushes.DarkRed,
                (bounds.Width - sz.Width) / 2f, (bounds.Height - sz.Height) / 2f);
            return;
        }

        int padding = 16;
        int xyBox = 240;
        var xyRect = new Rectangle(padding, padding, xyBox, xyBox);
        DrawXYPad(g, xyRect, snap.X, snap.Y);

        int barsX = xyRect.Right + padding;
        int barsWidth = 60;
        int barsHeight = xyBox;
        DrawVerticalBar(g, new Rectangle(barsX, padding, barsWidth, barsHeight),
            "Z", snap.Z ?? 0, Color.SteelBlue);
        DrawVerticalBar(g, new Rectangle(barsX + barsWidth + padding, padding, barsWidth, barsHeight),
            "RZ", snap.RotationZ ?? 0, Color.Teal);

        int povX = barsX + (barsWidth + padding) * 2;
        DrawPov(g, new Rectangle(povX, padding, 140, 140), snap.Pov);

        int buttonsTop = padding + xyBox + padding;
        DrawButtons(g, new Rectangle(padding, buttonsTop, bounds.Width - 2 * padding, 80), snap.Buttons);

        using var nameFont = new Font("Segoe UI", 10f, FontStyle.Italic);
        g.DrawString($"Urzadzenie: {snap.DeviceName}", nameFont, Brushes.Black,
            padding, buttonsTop + 90);
    }

    private static void DrawXYPad(Graphics g, Rectangle rect, float x, float y)
    {
        g.FillRectangle(Brushes.Gainsboro, rect);
        g.DrawRectangle(Pens.DimGray, rect);

        float cx = rect.Left + rect.Width / 2f;
        float cy = rect.Top + rect.Height / 2f;
        g.DrawLine(Pens.LightGray, rect.Left, cy, rect.Right, cy);
        g.DrawLine(Pens.LightGray, cx, rect.Top, cx, rect.Bottom);

        using var crosshairPen = new Pen(Color.DarkSlateGray, 2f);
        float px = cx + x * rect.Width / 2f;
        float py = cy + y * rect.Height / 2f;
        g.DrawLine(crosshairPen, px - 10, py, px + 10, py);
        g.DrawLine(crosshairPen, px, py - 10, px, py + 10);
        g.FillEllipse(Brushes.Crimson, px - 6, py - 6, 12, 12);

        using var label = new Font("Segoe UI", 8f);
        g.DrawString($"X={x:+0.00;-0.00;0.00}  Y={y:+0.00;-0.00;0.00}",
            label, Brushes.Black, rect.Left + 4, rect.Top + 4);
    }

    private static void DrawVerticalBar(Graphics g, Rectangle rect, string label, float value, Color color)
    {
        g.FillRectangle(Brushes.Gainsboro, rect);
        g.DrawRectangle(Pens.DimGray, rect);

        float v = (value + 1f) / 2f;
        v = Math.Clamp(v, 0f, 1f);
        int filledHeight = (int)(rect.Height * v);
        var filled = new Rectangle(rect.Left, rect.Bottom - filledHeight, rect.Width, filledHeight);
        using var br = new SolidBrush(color);
        g.FillRectangle(br, filled);

        using var f = new Font("Segoe UI", 9f, FontStyle.Bold);
        var lsz = g.MeasureString(label, f);
        g.DrawString(label, f, Brushes.Black, rect.Left + (rect.Width - lsz.Width) / 2, rect.Bottom + 2);
    }

    private static void DrawPov(Graphics g, Rectangle rect, int pov)
    {
        g.FillEllipse(Brushes.Gainsboro, rect);
        g.DrawEllipse(Pens.DimGray, rect);
        float cx = rect.Left + rect.Width / 2f;
        float cy = rect.Top + rect.Height / 2f;
        float radius = rect.Width / 2f - 8;

        using var f = new Font("Segoe UI", 9f, FontStyle.Bold);
        g.DrawString("POV", f, Brushes.Black, rect.Left + 4, rect.Top + 4);

        if (pov >= 0)
        {
            double angle = (pov / 100.0) * Math.PI / 180.0 - Math.PI / 2;
            float px = cx + (float)Math.Cos(angle) * radius;
            float py = cy + (float)Math.Sin(angle) * radius;
            using var arrow = new Pen(Color.DarkOrange, 4f) { EndCap = LineCap.ArrowAnchor };
            g.DrawLine(arrow, cx, cy, px, py);
        }
        else
        {
            g.FillEllipse(Brushes.DarkGray, cx - 5, cy - 5, 10, 10);
        }
    }

    private static void DrawButtons(Graphics g, Rectangle rect, bool[] buttons)
    {
        int count = Math.Min(buttons.Length, 16);
        if (count == 0) return;
        int columns = 8;
        int rows = (count + columns - 1) / columns;
        int gap = 6;
        int size = Math.Min((rect.Width - (columns + 1) * gap) / columns, 34);
        using var font = new Font("Segoe UI", 9f, FontStyle.Bold);

        for (int i = 0; i < count; i++)
        {
            int col = i % columns;
            int row = i / columns;
            int x = rect.Left + gap + col * (size + gap);
            int y = rect.Top + gap + row * (size + gap);
            var cell = new Rectangle(x, y, size, size);
            var brush = buttons[i] ? Brushes.LimeGreen : Brushes.LightGray;
            g.FillRectangle(brush, cell);
            g.DrawRectangle(Pens.DimGray, cell);
            string text = (i + 1).ToString();
            var ts = g.MeasureString(text, font);
            g.DrawString(text, font, Brushes.Black,
                x + (size - ts.Width) / 2, y + (size - ts.Height) / 2);
        }
    }
}
