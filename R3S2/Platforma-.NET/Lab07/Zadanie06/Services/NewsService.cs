using Zadanie06.Models;

namespace Zadanie06.Services;

public class NewsService
{
    private readonly List<NewsItem> _news = new()
    {
        new() { Id = 1, Title = "Otwarcie nowego laboratorium .NET", Content = "Uniwersytet uruchamia nowoczesne laboratorium programowania.", PublishedAt = DateTime.Now.AddHours(-2) },
        new() { Id = 2, Title = "Konferencja .NET 2026", Content = "W czerwcu odbędzie się coroczna konferencja poświęcona platformie .NET.", PublishedAt = DateTime.Now.AddHours(-5) }
    };

    private int _nextId = 3;

    public IReadOnlyList<NewsItem> GetAll() =>
        _news.OrderByDescending(n => n.PublishedAt).ToList().AsReadOnly();

    public void Add(NewsItem item)
    {
        item.Id = _nextId++;
        item.PublishedAt = DateTime.Now;
        _news.Add(item);
    }

    public void Delete(int id) => _news.RemoveAll(n => n.Id == id);
}
