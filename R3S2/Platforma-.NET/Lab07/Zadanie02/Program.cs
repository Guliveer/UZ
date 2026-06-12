using System.Text.Json;
using System.Text.Json.Serialization;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddHttpClient();

var app = builder.Build();
app.UseHttpsRedirection();

app.MapGet("/weather/{city}", async (string city, IHttpClientFactory httpClientFactory) =>
{
    var http = httpClientFactory.CreateClient();
    http.DefaultRequestHeaders.Add("User-Agent", "Zadanie02-WeatherApp/1.0");

    var geoUrl = $"https://geocoding-api.open-meteo.com/v1/search?name={Uri.EscapeDataString(city)}&count=1&language=pl&format=json";
    var geoResponse = await http.GetFromJsonAsync<GeoResponse>(geoUrl);

    if (geoResponse?.Results == null || geoResponse.Results.Length == 0)
        return Results.NotFound(new { error = $"Nie znaleziono miasta: {city}" });

    var location = geoResponse.Results[0];
    var weatherUrl = $"https://api.open-meteo.com/v1/forecast?latitude={location.Latitude}&longitude={location.Longitude}" +
        "&current=temperature_2m,relative_humidity_2m,wind_speed_10m,weather_code,apparent_temperature" +
        "&daily=temperature_2m_max,temperature_2m_min,precipitation_sum&timezone=auto&forecast_days=3";

    var weatherResponse = await http.GetFromJsonAsync<WeatherResponse>(weatherUrl);
    if (weatherResponse == null)
        return Results.Problem("Błąd pobierania danych pogodowych.");

    return Results.Ok(new
    {
        city = location.Name,
        country = location.Country,
        latitude = location.Latitude,
        longitude = location.Longitude,
        current = new
        {
            temperature = weatherResponse.Current.Temperature,
            apparent_temperature = weatherResponse.Current.ApparentTemperature,
            humidity = weatherResponse.Current.Humidity,
            wind_speed = weatherResponse.Current.WindSpeed,
            description = WmoCodeToDescription(weatherResponse.Current.WeatherCode)
        },
        forecast = Enumerable.Range(0, weatherResponse.Daily.Time.Length).Select(i => new
        {
            date = weatherResponse.Daily.Time[i],
            temp_max = weatherResponse.Daily.TempMax[i],
            temp_min = weatherResponse.Daily.TempMin[i],
            precipitation = weatherResponse.Daily.Precipitation[i]
        })
    });
});

app.Run();

static string WmoCodeToDescription(int code) => code switch
{
    0 => "Bezchmurnie",
    1 or 2 => "Przeważnie bezchmurnie",
    3 => "Zachmurzenie",
    45 or 48 => "Mgła",
    51 or 53 or 55 => "Mżawka",
    61 or 63 or 65 => "Deszcz",
    71 or 73 or 75 => "Śnieg",
    80 or 81 or 82 => "Przelotne opady deszczu",
    95 => "Burza",
    _ => $"Kod WMO: {code}"
};

record GeoResult(
    [property: JsonPropertyName("name")] string Name,
    [property: JsonPropertyName("latitude")] double Latitude,
    [property: JsonPropertyName("longitude")] double Longitude,
    [property: JsonPropertyName("country")] string Country);

record GeoResponse([property: JsonPropertyName("results")] GeoResult[]? Results);

record CurrentWeather(
    [property: JsonPropertyName("temperature_2m")] double Temperature,
    [property: JsonPropertyName("apparent_temperature")] double ApparentTemperature,
    [property: JsonPropertyName("relative_humidity_2m")] int Humidity,
    [property: JsonPropertyName("wind_speed_10m")] double WindSpeed,
    [property: JsonPropertyName("weather_code")] int WeatherCode);

record DailyWeather(
    [property: JsonPropertyName("time")] string[] Time,
    [property: JsonPropertyName("temperature_2m_max")] double[] TempMax,
    [property: JsonPropertyName("temperature_2m_min")] double[] TempMin,
    [property: JsonPropertyName("precipitation_sum")] double[] Precipitation);

record WeatherResponse(
    [property: JsonPropertyName("current")] CurrentWeather Current,
    [property: JsonPropertyName("daily")] DailyWeather Daily);
