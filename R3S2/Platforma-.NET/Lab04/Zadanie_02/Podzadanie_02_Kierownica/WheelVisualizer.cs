using System.Drawing.Drawing2D;

namespace KierownicaDemo;

public static class WheelVisualizer
{
    public static void Draw(Graphics g, Rectangle bounds, InputSnapshot snap)
    {
        g.SmoothingMode = SmoothingMode.AntiAlias;
        g.Clear(Color.White);

        if (!snap.IsConnected)
        {
            using var f = new Font("Segoe UI", 14f, FontStyle.Bold);
            var sz = g.MeasureString("Brak podlaczonej kierownicy", f);
            g.DrawString("Brak podlaczonej kierownicy", f, Brushes.DarkRed,
                (bounds.Width - sz.Width) / 2f, (bounds.Height - sz.Height) / 2f);
            return;
        }

        int wheelSize = 280;
        var wheelRect = new Rectangle(20, 20, wheelSize, wheelSize);
        DrawWheel(g, wheelRect, snap.X);

        int barX = wheelRect.Right + 40;
        int barWidth = 60;
        int barHeight = wheelSize;
        DrawPedal(g, new Rectangle(barX, 20, barWidth, barHeight), "Gaz", PedalValue(snap.Y), Color.LimeGreen);
        DrawPedal(g, new Rectangle(barX + barWidth + 30, 20, barWidth, barHeight), "Hamulec", PedalValue(snap.RotationZ), Color.Crimson);
        DrawPedal(g, new Rectangle(barX + (barWidth + 30) * 2, 20, barWidth, barHeight), "Sprzeglo", PedalValue(snap.Z), Color.SteelBlue);

        int gearX = barX + (barWidth + 30) * 3 + 10;
        DrawGear(g, new Rectangle(gearX, 20, 120, wheelSize / 2), snap.Pov);

        int buttonsTop = 20 + wheelSize + 20;
        DrawButtons(g, new Rectangle(20, buttonsTop, bounds.Width - 40, 80), snap.Buttons);

        using var nf = new Font("Segoe UI", 10f, FontStyle.Italic);
        g.DrawString($"Urzadzenie: {snap.DeviceName}", nf, Brushes.Black, 20, buttonsTop + 90);
    }

    private static float PedalValue(float? axis)
    {
        if (axis is null) return 0f;
        return Math.Clamp((axis.Value + 1f) / 2f, 0f, 1f);
    }

    private static void DrawWheel(Graphics g, Rectangle rect, float steering)
    {
        float cx = rect.Left + rect.Width / 2f;
        float cy = rect.Top + rect.Height / 2f;
        float outer = rect.Width / 2f;
        float inner = outer * 0.65f;

        using (var rim = new Pen(Color.FromArgb(40, 40, 40), 14f))
            g.DrawEllipse(rim, rect);

        var state = g.Save();
        g.TranslateTransform(cx, cy);
        g.RotateTransform(steering * 450f);

        using (var spokePen = new Pen(Color.DimGray, 8f) { EndCap = LineCap.Round, StartCap = LineCap.Round })
        {
            g.DrawLine(spokePen, -inner, 0, inner, 0);
            g.DrawLine(spokePen, 0, 0, 0, inner);
        }
        using (var hub = new SolidBrush(Color.FromArgb(90, 90, 90)))
            g.FillEllipse(hub, -outer * 0.22f, -outer * 0.22f, outer * 0.44f, outer * 0.44f);
        using (var logo = new Font("Segoe UI", 14f, FontStyle.Bold))
            g.DrawString("UZ", logo, Brushes.White, -14f, -11f);
        g.Restore(state);

        using var f = new Font("Segoe UI", 9f);
        g.DrawString($"Skret: {steering * 450f:+000;-000;000}°",
            f, Brushes.Black, rect.Left, rect.Bottom + 4);
    }

    private static void DrawPedal(Graphics g, Rectangle rect, string label, float value, Color color)
    {
        g.FillRectangle(Brushes.Gainsboro, rect);
        g.DrawRectangle(Pens.DimGray, rect);
        int h = (int)(rect.Height * value);
        var fill = new Rectangle(rect.Left, rect.Bottom - h, rect.Width, h);
        using var br = new SolidBrush(color);
        g.FillRectangle(br, fill);

        using var f = new Font("Segoe UI", 9f, FontStyle.Bold);
        var ls = g.MeasureString(label, f);
        g.DrawString(label, f, Brushes.Black,
            rect.Left + (rect.Width - ls.Width) / 2, rect.Bottom + 4);

        string pct = $"{(int)(value * 100)}%";
        var ps = g.MeasureString(pct, f);
        g.DrawString(pct, f, Brushes.White, rect.Left + (rect.Width - ps.Width) / 2, rect.Top + 4);
    }

    private static void DrawGear(Graphics g, Rectangle rect, int pov)
    {
        g.FillRectangle(Brushes.Black, rect);
        g.DrawRectangle(Pens.DimGray, rect);

        string gearText = pov switch
        {
            -1 => "N",
            0 => "1",
            9000 => "2",
            18000 => "3",
            27000 => "4",
            4500 => "5",
            13500 => "6",
            22500 => "R",
            _ => (pov / 4500 + 1).ToString()
        };

        using var f = new Font("Consolas", 48f, FontStyle.Bold);
        var sz = g.MeasureString(gearText, f);
        g.DrawString(gearText, f, Brushes.LimeGreen,
            rect.Left + (rect.Width - sz.Width) / 2,
            rect.Top + (rect.Height - sz.Height) / 2);

        using var small = new Font("Segoe UI", 9f);
        g.DrawString("Bieg", small, Brushes.LimeGreen, rect.Left + 6, rect.Top + 4);
    }

    private static void DrawButtons(Graphics g, Rectangle rect, bool[] buttons)
    {
        int count = Math.Min(buttons.Length, 16);
        if (count == 0) return;
        int columns = 8;
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
