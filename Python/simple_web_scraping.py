import requests
from bs4 import BeautifulSoup

def basic_scraper():

    url = 'https://www.example.com'
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    print(soup)  #Print complete scraped data

    title = soup.select_one('h1').text
    text = soup.select_one('p').text
    link = soup.select_one('a').get('href')

    print(f"title: {title}\ntext: {title}\nlink: {link}")
    print(text)
    print(link)


if __name__ == '__main__':
    basic_scraper()