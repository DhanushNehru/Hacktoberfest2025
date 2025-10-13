import requests
from bs4 import BeautifulSoup
import csv
import json
from urllib.parse import urljoin, urlparse
import time

class WebScraper:
    """A versatile web scraper with multiple extraction methods"""
    
    def __init__(self, headers=None):
        """Initialize the scraper with optional custom headers"""
        self.headers = headers or {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36'
        }
    
    def fetch_page(self, url, timeout=10):
        """
        Fetch a webpage and return BeautifulSoup object.
        
        Args:
            url: URL to fetch
            timeout: Request timeout in seconds
        
        Returns:
            BeautifulSoup object or None if error
        """
        try:
            response = requests.get(url, headers=self.headers, timeout=timeout)
            response.raise_for_status()
            return BeautifulSoup(response.content, 'html.parser')
        
        except requests.exceptions.RequestException as e:
            print(f"Error fetching {url}: {e}")
            return None
    
    def extract_text(self, url, selector=None):
        """
        Extract text content from a webpage.
        
        Args:
            url: URL to scrape
            selector: CSS selector (optional)
        
        Returns:
            List of text content
        """
        soup = self.fetch_page(url)
        if not soup:
            return []
        
        if selector:
            elements = soup.select(selector)
            return [elem.get_text(strip=True) for elem in elements]
        else:
            return [soup.get_text(strip=True)]
    
    def extract_links(self, url, base_url=None, internal_only=True):
        """
        Extract all links from a webpage.
        
        Args:
            url: URL to scrape
            base_url: Base URL for resolving relative links
            internal_only: Only return links from same domain
        
        Returns:
            List of URLs
        """
        soup = self.fetch_page(url)
        if not soup:
            return []
        
        base_url = base_url or url
        domain = urlparse(base_url).netloc
        links = []
        
        for link in soup.find_all('a', href=True):
            href = link['href']
            full_url = urljoin(base_url, href)
            
            if internal_only:
                if urlparse(full_url).netloc == domain:
                    links.append(full_url)
            else:
                links.append(full_url)
        
        return list(set(links))  # Remove duplicates
    
    def extract_images(self, url, base_url=None):
        """
        Extract all image URLs from a webpage.
        
        Args:
            url: URL to scrape
            base_url: Base URL for resolving relative URLs
        
        Returns:
            List of image URLs
        """
        soup = self.fetch_page(url)
        if not soup:
            return []
        
        base_url = base_url or url
        images = []
        
        for img in soup.find_all('img', src=True):
            img_url = urljoin(base_url, img['src'])
            images.append({
                'url': img_url,
                'alt': img.get('alt', ''),
                'title': img.get('title', '')
            })
        
        return images
    
    def extract_table(self, url, table_index=0):
        """
        Extract table data from a webpage.
        
        Args:
            url: URL to scrape
            table_index: Index of table to extract (0-based)
        
        Returns:
            List of lists (rows and columns)
        """
        soup = self.fetch_page(url)
        if not soup:
            return []
        
        tables = soup.find_all('table')
        if not tables or table_index >= len(tables):
            print(f"Table at index {table_index} not found")
            return []
        
        table = tables[table_index]
        data = []
        
        # Extract headers
        headers = []
        header_row = table.find('thead')
        if header_row:
            headers = [th.get_text(strip=True) for th in header_row.find_all(['th', 'td'])]
            data.append(headers)
        
        # Extract rows
        rows = table.find_all('tr')
        for row in rows:
            cols = row.find_all(['td', 'th'])
            if cols:
                data.append([col.get_text(strip=True) for col in cols])
        
        return data
    
    def scrape_custom(self, url, config):
        """
        Scrape data based on custom configuration.
        
        Args:
            url: URL to scrape
            config: Dictionary with selectors and field names
                   Example: {'title': 'h1', 'price': '.price', 'description': 'p.desc'}
        
        Returns:
            Dictionary with extracted data
        """
        soup = self.fetch_page(url)
        if not soup:
            return {}
        
        result = {}
        for field, selector in config.items():
            elements = soup.select(selector)
            if elements:
                if len(elements) == 1:
                    result[field] = elements[0].get_text(strip=True)
                else:
                    result[field] = [elem.get_text(strip=True) for elem in elements]
            else:
                result[field] = None
        
        return result
    
    def save_to_csv(self, data, filename):
        """Save scraped data to CSV file"""
        if not data:
            print("No data to save")
            return
        
        try:
            with open(filename, 'w', newline='', encoding='utf-8') as f:
                if isinstance(data[0], dict):
                    writer = csv.DictWriter(f, fieldnames=data[0].keys())
                    writer.writeheader()
                    writer.writerows(data)
                else:
                    writer = csv.writer(f)
                    writer.writerows(data)
            
            print(f"Data saved to {filename}")
        
        except Exception as e:
            print(f"Error saving to CSV: {e}")
    
    def save_to_json(self, data, filename):
        """Save scraped data to JSON file"""
        try:
            with open(filename, 'w', encoding='utf-8') as f:
                json.dump(data, f, indent=2, ensure_ascii=False)
            print(f"Data saved to {filename}")
        
        except Exception as e:
            print(f"Error saving to JSON: {e}")


def demo():
    """Demonstration of web scraper functionality"""
    print("Web Scraper Demo")
    print("=" * 60)
    
    scraper = WebScraper()
    
    # Example 1: Extract links from a webpage
    print("\n1. Extracting links from Python.org...")
    url = "https://www.python.org"
    links = scraper.extract_links(url, internal_only=True)
    print(f"Found {len(links)} internal links")
    print("First 5 links:", links[:5])
    
    # Example 2: Extract images
    print("\n2. Extracting images...")
    images = scraper.extract_images(url)
    print(f"Found {len(images)} images")
    if images:
        print("First image:", images[0])
    
    # Example 3: Custom scraping
    print("\n3. Custom scraping example...")
    config = {
        'title': 'h1',
        'paragraphs': 'p'
    }
    data = scraper.scrape_custom(url, config)
    print(f"Extracted data: {data}")
    
    print("\n" + "=" * 60)
    print("Note: Always check robots.txt and terms of service")
    print("before scraping any website!")


if __name__ == "__main__":
    demo()
    
    # Interactive mode
    print("\n" + "=" * 60)
    print("Interactive Mode")
    print("=" * 60)
    
    scraper = WebScraper()
    
    while True:
        print("\nOptions:")
        print("1. Extract text")
        print("2. Extract links")
        print("3. Extract images")
        print("4. Extract table")
        print("5. Quit")
        
        choice = input("\nEnter choice (1-5): ").strip()
        
        if choice == '5':
            print("Goodbye!")
            break
        
        if choice in ['1', '2', '3', '4']:
            url = input("Enter URL: ").strip()
            if not url:
                print("Invalid URL")
                continue
            
            if choice == '1':
                text = scraper.extract_text(url)
                print(f"\nExtracted text (first 500 chars):\n{text[0][:500] if text else 'No text found'}")
            
            elif choice == '2':
                links = scraper.extract_links(url)
                print(f"\nFound {len(links)} links:")
                for i, link in enumerate(links[:10], 1):
                    print(f"{i}. {link}")
            
            elif choice == '3':
                images = scraper.extract_images(url)
                print(f"\nFound {len(images)} images:")
                for i, img in enumerate(images[:10], 1):
                    print(f"{i}. {img['url']}")
            
            elif choice == '4':
                table_data = scraper.extract_table(url)
                print(f"\nExtracted table ({len(table_data)} rows):")
                for row in table_data[:5]:
                    print(row)
        else:
            print("Invalid choice")
