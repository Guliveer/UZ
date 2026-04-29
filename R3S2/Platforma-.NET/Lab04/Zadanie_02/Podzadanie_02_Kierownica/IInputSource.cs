namespace KierownicaDemo;

public interface IInputSource : IDisposable
{
    string Name { get; }
    bool IsAvailable { get; }
    InputSnapshot Poll();
}
