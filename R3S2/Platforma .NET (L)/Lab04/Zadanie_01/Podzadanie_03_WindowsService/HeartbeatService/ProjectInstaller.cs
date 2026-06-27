using System.ComponentModel;
using System.Configuration.Install;
using System.ServiceProcess;

namespace HeartbeatService
{
    [RunInstaller(true)]
    public class ProjectInstaller : Installer
    {
        private readonly ServiceProcessInstaller _processInstaller;
        private readonly ServiceInstaller _serviceInstaller;

        public ProjectInstaller()
        {
            _processInstaller = new ServiceProcessInstaller
            {
                Account = ServiceAccount.LocalSystem
            };

            _serviceInstaller = new ServiceInstaller
            {
                ServiceName = HeartbeatService.HeartbeatServiceName,
                DisplayName = "Heartbeat Demo Service (Lab04)",
                Description = "Demo Windows Service z Lab04 (Zadanie_01/Podzadanie_03). Co 10s zapisuje wpis do Event Log.",
                StartType = ServiceStartMode.Manual
            };

            Installers.Add(_processInstaller);
            Installers.Add(_serviceInstaller);
        }
    }
}
