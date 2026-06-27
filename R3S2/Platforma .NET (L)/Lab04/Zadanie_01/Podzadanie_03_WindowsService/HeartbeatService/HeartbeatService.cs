using System;
using System.Diagnostics;
using System.ServiceProcess;
using System.Timers;

namespace HeartbeatService
{
    public partial class HeartbeatService : ServiceBase
    {
        public const string HeartbeatServiceName = "HeartbeatService";
        public const string LogSource = "HeartbeatService";
        public const string LogName = "Application";

        private Timer _timer;
        private EventLog _eventLog;

        public HeartbeatService()
        {
            ServiceName = HeartbeatServiceName;
            CanStop = true;
            CanPauseAndContinue = false;
            AutoLog = false;

            if (!EventLog.SourceExists(LogSource))
                EventLog.CreateEventSource(LogSource, LogName);

            _eventLog = new EventLog { Source = LogSource, Log = LogName };
        }

        protected override void OnStart(string[] args)
        {
            _eventLog.WriteEntry(
                $"Usluga uruchomiona {DateTime.Now:yyyy-MM-dd HH:mm:ss}.",
                EventLogEntryType.Information);

            _timer = new Timer(10_000) { AutoReset = true };
            _timer.Elapsed += OnHeartbeat;
            _timer.Start();
        }

        protected override void OnStop()
        {
            _timer?.Stop();
            _timer?.Dispose();
            _eventLog.WriteEntry(
                $"Usluga zatrzymana {DateTime.Now:yyyy-MM-dd HH:mm:ss}.",
                EventLogEntryType.Information);
        }

        private void OnHeartbeat(object sender, ElapsedEventArgs e)
        {
            _eventLog.WriteEntry(
                $"Heartbeat {e.SignalTime:yyyy-MM-dd HH:mm:ss}",
                EventLogEntryType.Information);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                _timer?.Dispose();
                _eventLog?.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
