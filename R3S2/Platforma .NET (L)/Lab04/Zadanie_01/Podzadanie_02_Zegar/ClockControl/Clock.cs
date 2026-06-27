using System.ComponentModel;
using System.Drawing.Drawing2D;

namespace ClockControl;

[ToolboxItem(true)]
[DefaultProperty(nameof(DisplayMode))]
[Description("Zegar w trybie analogowym lub cyfrowym, z aktualnym czasem systemowym.")]
public class Clock : UserControl
{
    private readonly System.Windows.Forms.Timer _timer;

    private ClockDisplayMode _displayMode = ClockDisplayMode.Analog;
    private bool _showSeconds = true;
    private Color _faceColor = Color.White;
    private Color _hourHandColor = Color.Black;
    private Color _minuteHandColor = Color.DarkSlateGray;
    private Color _secondHandColor = Color.Crimson;
    private Color _digitalColor = Color.LimeGreen;
    private Color _digitalBackColor = Color.Black;
    private Font _digitalFont = new("Consolas", 36f, FontStyle.Bold);
    private DateTime _overrideTime;
    private bool _useOverrideTime;

    public Clock()
    {
        SetStyle(
            ControlStyles.AllPaintingInWmPaint |
            ControlStyles.OptimizedDoubleBuffer |
            ControlStyles.ResizeRedraw |
            ControlStyles.UserPaint,
            true);

        Size = new Size(200, 200);
        DoubleBuffered = true;

        _timer = new System.Windows.Forms.Timer { Interval = 500 };
        _timer.Tick += (_, _) => Invalidate();
        _timer.Start();
    }

    [Category("Zegar")]
    [Description("Tryb wyswietlania: analogowy lub cyfrowy.")]
    [DefaultValue(ClockDisplayMode.Analog)]
    public ClockDisplayMode DisplayMode
    {
        get => _displayMode;
        set
        {
            if (_displayMode == value) return;
            _displayMode = value;
            Invalidate();
        }
    }

    [Category("Zegar")]
    [DefaultValue(true)]
    public bool ShowSeconds
    {
        get => _showSeconds;
        set { _showSeconds = value; Invalidate(); }
    }

    [Category("Zegar")]
    [DefaultValue(typeof(Color), "White")]
    public Color FaceColor
    {
        get => _faceColor;
        set { _faceColor = value; Invalidate(); }
    }

    [Category("Zegar")]
    [DefaultValue(typeof(Color), "Black")]
    public Color HourHandColor
    {
        get => _hourHandColor;
        set { _hourHandColor = value; Invalidate(); }
    }

    [Category("Zegar")]
    [DefaultValue(typeof(Color), "DarkSlateGray")]
    public Color MinuteHandColor
    {
        get => _minuteHandColor;
        set { _minuteHandColor = value; Invalidate(); }
    }

    [Category("Zegar")]
    [DefaultValue(typeof(Color), "Crimson")]
    public Color SecondHandColor
    {
        get => _secondHandColor;
        set { _secondHandColor = value; Invalidate(); }
    }

    [Category("Zegar")]
    [DefaultValue(typeof(Color), "LimeGreen")]
    public Color DigitalColor
    {
        get => _digitalColor;
        set { _digitalColor = value; Invalidate(); }
    }

    [Category("Zegar")]
    [DefaultValue(typeof(Color), "Black")]
    public Color DigitalBackColor
    {
        get => _digitalBackColor;
        set { _digitalBackColor = value; Invalidate(); }
    }

    [Category("Zegar")]
    [DesignerSerializationVisibility(DesignerSerializationVisibility.Visible)]
    public Font DigitalFont
    {
        get => _digitalFont;
        set { _digitalFont = value ?? throw new ArgumentNullException(nameof(value)); Invalidate(); }
    }

    /// <summary>
    /// Ustawia konkretny czas (pomija zegar systemowy) — przydatne do testow i prezentacji.
    /// Wywolaj <see cref="ResetTime"/> zeby wrocic do czasu systemowego.
    /// </summary>
    public void SetTime(DateTime time)
    {
        _overrideTime = time;
        _useOverrideTime = true;
        Invalidate();
    }

    public void ResetTime()
    {
        _useOverrideTime = false;
        Invalidate();
    }

    private DateTime CurrentTime => _useOverrideTime ? _overrideTime : DateTime.Now;

    protected override void OnPaint(PaintEventArgs e)
    {
        base.OnPaint(e);
        var g = e.Graphics;
        g.SmoothingMode = SmoothingMode.AntiAlias;
        g.TextRenderingHint = System.Drawing.Text.TextRenderingHint.AntiAlias;

        if (_displayMode == ClockDisplayMode.Analog)
            DrawAnalog(g);
        else
            DrawDigital(g);
    }

    private void DrawAnalog(Graphics g)
    {
        var time = CurrentTime;
        int size = Math.Min(ClientSize.Width, ClientSize.Height) - 4;
        var center = new PointF(ClientSize.Width / 2f, ClientSize.Height / 2f);
        float radius = size / 2f;
        var face = new RectangleF(center.X - radius, center.Y - radius, size, size);

        using (var faceBrush = new SolidBrush(FaceColor))
            g.FillEllipse(faceBrush, face);
        using (var ring = new Pen(Color.FromArgb(60, 60, 60), 3f))
            g.DrawEllipse(ring, face);

        for (int i = 0; i < 60; i++)
        {
            double angle = Math.PI * 2 * i / 60 - Math.PI / 2;
            float outerX = center.X + (float)Math.Cos(angle) * radius;
            float outerY = center.Y + (float)Math.Sin(angle) * radius;
            float inner = i % 5 == 0 ? radius * 0.88f : radius * 0.94f;
            float innerX = center.X + (float)Math.Cos(angle) * inner;
            float innerY = center.Y + (float)Math.Sin(angle) * inner;
            using var tickPen = new Pen(Color.Black, i % 5 == 0 ? 2.5f : 1f);
            g.DrawLine(tickPen, innerX, innerY, outerX, outerY);
        }

        for (int h = 1; h <= 12; h++)
        {
            double angle = Math.PI * 2 * h / 12 - Math.PI / 2;
            float tx = center.X + (float)Math.Cos(angle) * radius * 0.75f;
            float ty = center.Y + (float)Math.Sin(angle) * radius * 0.75f;
            using var f = new Font("Segoe UI", radius * 0.12f, FontStyle.Bold);
            var text = h.ToString();
            var sz = g.MeasureString(text, f);
            g.DrawString(text, f, Brushes.Black, tx - sz.Width / 2, ty - sz.Height / 2);
        }

        float hourAngle = ((time.Hour % 12) + time.Minute / 60f) * 30f - 90f;
        float minuteAngle = (time.Minute + time.Second / 60f) * 6f - 90f;
        float secondAngle = time.Second * 6f - 90f;

        DrawHand(g, center, radius * 0.5f, hourAngle, HourHandColor, 6f);
        DrawHand(g, center, radius * 0.75f, minuteAngle, MinuteHandColor, 4f);
        if (ShowSeconds)
            DrawHand(g, center, radius * 0.85f, secondAngle, SecondHandColor, 1.5f);

        using var capBrush = new SolidBrush(HourHandColor);
        float capR = radius * 0.05f;
        g.FillEllipse(capBrush, center.X - capR, center.Y - capR, capR * 2, capR * 2);
    }

    private static void DrawHand(Graphics g, PointF center, float length, float angleDeg, Color color, float width)
    {
        double rad = angleDeg * Math.PI / 180.0;
        float x = center.X + (float)Math.Cos(rad) * length;
        float y = center.Y + (float)Math.Sin(rad) * length;
        using var pen = new Pen(color, width) { StartCap = LineCap.Round, EndCap = LineCap.Round };
        g.DrawLine(pen, center.X, center.Y, x, y);
    }

    private void DrawDigital(Graphics g)
    {
        var time = CurrentTime;
        using (var bg = new SolidBrush(DigitalBackColor))
            g.FillRectangle(bg, ClientRectangle);

        string text = ShowSeconds ? time.ToString("HH:mm:ss") : time.ToString("HH:mm");
        var sz = g.MeasureString(text, DigitalFont);
        float x = (ClientSize.Width - sz.Width) / 2f;
        float y = (ClientSize.Height - sz.Height) / 2f;

        using var shadowBrush = new SolidBrush(Color.FromArgb(90, DigitalColor));
        g.DrawString(text, DigitalFont, shadowBrush, x + 2, y + 2);
        using var fg = new SolidBrush(DigitalColor);
        g.DrawString(text, DigitalFont, fg, x, y);

        string date = time.ToString("yyyy-MM-dd  dddd");
        using var small = new Font(DigitalFont.FontFamily, DigitalFont.Size * 0.3f, FontStyle.Regular);
        var dsz = g.MeasureString(date, small);
        using var dfg = new SolidBrush(Color.FromArgb(180, DigitalColor));
        g.DrawString(date, small, dfg, (ClientSize.Width - dsz.Width) / 2f, y + sz.Height + 2);
    }

    protected override void Dispose(bool disposing)
    {
        if (disposing)
        {
            _timer.Stop();
            _timer.Dispose();
            _digitalFont.Dispose();
        }
        base.Dispose(disposing);
    }
}
