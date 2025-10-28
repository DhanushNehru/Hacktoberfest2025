async Task<int> FetchDataAsync() {
    await Task.Delay(2000); // simulate delay
    return 42;
}

async Task Main() {
    int result = await FetchDataAsync();
    Console.WriteLine(result);
}
