using System.ComponentModel;
using System.Drawing.Drawing2D;

namespace TrafficLightControl;

[ToolboxItem(true)]
[DefaultProperty(nameof(State))]
[Description("Sygnalizator swietlny z regulowana liczba lamp (1/2/3) i orientacja.")]
public class TrafficLight : UserControl
{
    private TrafficLightLampCount _lampCount = TrafficLightLampCount.Three;
    private TrafficLightState _state = TrafficLightState.Red;
    private TrafficLightOrientation _orientation = TrafficLightOrientation.Vertical;
    private int _lampDiameter = 60;
    private int _lampSpacing = 10;
    private Color _housingColor = Color.Black;
    private Color _offColor = Color.DimGray;

    public TrafficLight()
    {
        SetStyle(
            ControlStyles.AllPaintingInWmPaint |
            ControlStyles.OptimizedDoubleBuffer |
            ControlStyles.ResizeRedraw |
            ControlStyles.UserPaint,
            true);

        BackColor = Color.Transparent;
        Size = new Size(90, 220);
    }

    [Category("Sygnalizator")]
    [Description("Liczba widocznych pol (1, 2 lub 3).")]
    [DefaultValue(TrafficLightLampCount.Three)]
    public TrafficLightLampCount LampCount
    {
        get => _lampCount;
        set
        {
            if (_lampCount == value) return;
            _lampCount = value;
            OnLampCountChanged(EventArgs.Empty);
            Invalidate();
        }
    }

    [Category("Sygnalizator")]
    [Description("Aktualny stan sygnalizatora.")]
    [DefaultValue(TrafficLightState.Red)]
    public TrafficLightState State
    {
        get => _state;
        set
        {
            if (_state == value) return;
            _state = value;
            OnStateChanged(EventArgs.Empty);
            Invalidate();
        }
    }

    [Category("Sygnalizator")]
    [Description("Ulozenie pol: pionowe lub poziome.")]
    [DefaultValue(TrafficLightOrientation.Vertical)]
    public TrafficLightOrientation Orientation
    {
        get => _orientation;
        set
        {
            if (_orientation == value) return;
            _orientation = value;
            Invalidate();
        }
    }

    [Category("Sygnalizator")]
    [Description("Srednica pojedynczej lampy w pikselach.")]
    [DefaultValue(60)]
    public int LampDiameter
    {
        get => _lampDiameter;
        set
        {
            if (_lampDiameter == value) return;
            _lampDiameter = Math.Max(8, value);
            Invalidate();
        }
    }

    [Category("Sygnalizator")]
    [Description("Odstep miedzy lampami w pikselach.")]
    [DefaultValue(10)]
    public int LampSpacing
    {
        get => _lampSpacing;
        set
        {
            if (_lampSpacing == value) return;
            _lampSpacing = Math.Max(0, value);
            Invalidate();
        }
    }

    [Category("Sygnalizator")]
    [Description("Kolor obudowy sygnalizatora.")]
    [DefaultValue(typeof(Color), "Black")]
    public Color HousingColor
    {
        get => _housingColor;
        set { _housingColor = value; Invalidate(); }
    }

    [Category("Sygnalizator")]
    [Description("Kolor wygaszonej lampy.")]
    [DefaultValue(typeof(Color), "DimGray")]
    public Color OffColor
    {
        get => _offColor;
        set { _offColor = value; Invalidate(); }
    }

    [Category("Sygnalizator")]
    [Description("Wywolywane przy zmianie stanu.")]
    public event EventHandler? StateChanged;

    [Category("Sygnalizator")]
    [Description("Wywolywane przy zmianie liczby lamp.")]
    public event EventHandler? LampCountChanged;

    protected virtual void OnStateChanged(EventArgs e) => StateChanged?.Invoke(this, e);
    protected virtual void OnLampCountChanged(EventArgs e) => LampCountChanged?.Invoke(this, e);

    /// <summary>
    /// Zwraca tablice logicznych wartosci <c>true</c> = lampa zapalona, w kolejnosci
    /// indeksow zgodnej z <see cref="GetLampColors"/>.
    /// </summary>
    protected bool[] GetLitLamps()
    {
        int count = (int)LampCount;
        var lit = new bool[count];

        switch (LampCount)
        {
            case TrafficLightLampCount.One:
                lit[0] = State != TrafficLightState.Off;
                break;

            case TrafficLightLampCount.Two:
                lit[0] = State is TrafficLightState.Red or TrafficLightState.RedYellow;
                lit[1] = State is TrafficLightState.Green;
                break;

            case TrafficLightLampCount.Three:
                lit[0] = State is TrafficLightState.Red or TrafficLightState.RedYellow;
                lit[1] = State is TrafficLightState.Yellow or TrafficLightState.RedYellow;
                lit[2] = State is TrafficLightState.Green;
                break;
        }

        return lit;
    }

    /// <summary>
    /// Kolory lamp po kolei: Red, (Yellow), Green — zaleznie od LampCount.
    /// </summary>
    protected Color[] GetLampColors() => LampCount switch
    {
        TrafficLightLampCount.One => new[] { ColorForSingleLamp() },
        TrafficLightLampCount.Two => new[] { Color.Red, Color.Green },
        TrafficLightLampCount.Three => new[] { Color.Red, Color.Gold, Color.LimeGreen },
        _ => Array.Empty<Color>()
    };

    private Color ColorForSingleLamp() => State switch
    {
        TrafficLightState.Red or TrafficLightState.RedYellow => Color.Red,
        TrafficLightState.Yellow => Color.Gold,
        TrafficLightState.Green => Color.LimeGreen,
        _ => OffColor
    };

    protected override void OnPaint(PaintEventArgs e)
    {
        base.OnPaint(e);
        var g = e.Graphics;
        g.SmoothingMode = SmoothingMode.AntiAlias;

        var lit = GetLitLamps();
        var colors = GetLampColors();
        int n = (int)LampCount;
        int d = LampDiameter;
        int s = LampSpacing;

        int along = n * d + (n + 1) * s;
        int across = d + 2 * s;
        int housingW = Orientation == TrafficLightOrientation.Vertical ? across : along;
        int housingH = Orientation == TrafficLightOrientation.Vertical ? along : across;

        int offsetX = Math.Max(0, (ClientSize.Width - housingW) / 2);
        int offsetY = Math.Max(0, (ClientSize.Height - housingH) / 2);

        var housing = new Rectangle(offsetX, offsetY, housingW, housingH);
        using (var housingBrush = new SolidBrush(HousingColor))
            g.FillRectangle(housingBrush, housing);
        using (var framePen = new Pen(Color.FromArgb(40, 40, 40), 2f))
            g.DrawRectangle(framePen, housing);

        for (int i = 0; i < n; i++)
        {
            int lx = offsetX + s + (Orientation == TrafficLightOrientation.Horizontal ? i * (d + s) : 0);
            int ly = offsetY + s + (Orientation == TrafficLightOrientation.Vertical ? i * (d + s) : 0);
            var lampRect = new Rectangle(lx, ly, d, d);

            Color face = lit[i] ? colors[i] : OffColor;
            using (var path = new GraphicsPath())
            {
                path.AddEllipse(lampRect);
                using var glow = new PathGradientBrush(path)
                {
                    CenterColor = ControlPaint.Light(face, lit[i] ? 0.6f : 0f),
                    SurroundColors = new[] { face }
                };
                g.FillEllipse(glow, lampRect);
            }
            using var outline = new Pen(Color.FromArgb(30, 0, 0, 0), 1.5f);
            g.DrawEllipse(outline, lampRect);
        }
    }

    /// <summary>
    /// Zwraca preferowany rozmiar komponentu na podstawie LampCount / LampDiameter / Orientation.
    /// </summary>
    public override Size GetPreferredSize(Size proposedSize)
    {
        int n = (int)LampCount;
        int d = LampDiameter;
        int s = LampSpacing;
        int along = n * d + (n + 1) * s;
        int across = d + 2 * s;
        return Orientation == TrafficLightOrientation.Vertical
            ? new Size(across, along)
            : new Size(along, across);
    }
}
