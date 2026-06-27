using System.ServiceProcess;

namespace HeartbeatService
{
    static class Program
    {
        static void Main()
        {
            ServiceBase.Run(new ServiceBase[] { new HeartbeatService() });
        }
    }
}
