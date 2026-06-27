using System.Globalization;

namespace Zadanie_01;

public partial class MainPage : ContentPage
{
    private bool _alarmSet;
    private TimeSpan _alarmTime;
    private bool _alarmTriggered;
    private IDispatcherTimer? _timer;

    public MainPage()
    {
        InitializeComponent();
        UpdateDisplay();
    }

    protected override void OnAppearing()
    {
        base.OnAppearing();
        _timer = Dispatcher.CreateTimer();
        _timer.Interval = TimeSpan.FromMilliseconds(500);
        _timer.Tick += OnTimerTick;
        _timer.Start();
    }

    protected override void OnDisappearing()
    {
        base.OnDisappearing();
        _timer?.Stop();
    }

    private void OnTimerTick(object? sender, EventArgs e)
    {
        UpdateDisplay();
        CheckAlarm();
    }

    private void UpdateDisplay()
    {
        var now = DateTime.Now;
        TimeLabel.Text = now.ToString("HH:mm:ss");
        DateLabel.Text = FormatDate(now);
    }

    private void CheckAlarm()
    {
        if (!_alarmSet || _alarmTriggered) return;

        var now = DateTime.Now.TimeOfDay;
        if (now.Hours == _alarmTime.Hours &&
            now.Minutes == _alarmTime.Minutes &&
            now.Seconds == _alarmTime.Seconds)
        {
            _alarmTriggered = true;
            _ = Dispatcher.DispatchAsync(async () =>
            {
                await DisplayAlertAsync("Alarm", $"Alarm! Jest {DateTime.Now:HH:mm:ss}", "OK");
                ResetAlarm();
            });
        }
    }

    private void OnSetAlarm(object? sender, EventArgs e)
    {
        _alarmTime = AlarmPicker.Time ?? TimeSpan.Zero;
        _alarmSet = true;
        _alarmTriggered = false;
        SetAlarmBtn.IsEnabled = false;
        CancelAlarmBtn.IsEnabled = true;
        AlarmPicker.IsEnabled = false;
        AlarmStatusLabel.Text = $"Alarm ustawiony na: {_alarmTime:hh\\:mm\\:ss}";
        AlarmStatusLabel.TextColor = Colors.DarkGreen;
    }

    private void OnCancelAlarm(object? sender, EventArgs e)
    {
        ResetAlarm();
    }

    private void ResetAlarm()
    {
        _alarmSet = false;
        _alarmTriggered = false;
        SetAlarmBtn.IsEnabled = true;
        CancelAlarmBtn.IsEnabled = false;
        AlarmPicker.IsEnabled = true;
        AlarmStatusLabel.Text = "Alarm: nie ustawiony";
        AlarmStatusLabel.TextColor = Colors.Gray;
    }

    private static string FormatDate(DateTime date)
    {
        var culture = new CultureInfo("pl-PL");
        var dayOfWeek = culture.DateTimeFormat.GetDayName(date.DayOfWeek);
        dayOfWeek = char.ToUpper(dayOfWeek[0]) + dayOfWeek[1..];
        return $"{dayOfWeek}, {date.Day} {culture.DateTimeFormat.GetMonthName(date.Month)} {date.Year}";
    }
}