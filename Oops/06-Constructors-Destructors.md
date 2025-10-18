# Constructors and Destructors 🏗️💥

## What are Constructors and Destructors?

**Constructor**: A special method that initializes objects when they're created. It's like the "birth certificate" of an object.

**Destructor**: A special method that cleans up when objects are destroyed. It's like the "cleanup crew" that runs when an object's life ends.

## Real-World Analogy

**Constructor = Setting up a new apartment**

- Turn on utilities (electricity, water, internet)
- Set up furniture and belongings
- Get keys and security codes
- Register with building management

**Destructor = Moving out of apartment**

- Pack up belongings
- Turn off utilities
- Return keys
- Clean up and restore to original state

## Types of Constructors

### 1. **Default Constructor**

No parameters, provides default values.

### 2. **Parameterized Constructor**

Takes parameters to initialize with specific values.

### 3. **Copy Constructor**

Creates a new object as a copy of an existing object.

### 4. **Move Constructor** (C++)

Transfers resources from a temporary object.

## Implementation Examples

### Python Example

```python
import datetime
import weakref

class SmartPhone:
    # Class variable to track all phones
    all_phones = []
    phone_count = 0

    def __init__(self, brand, model, storage_gb=64, color="Black"):
        """
        Constructor - Initialize a new smartphone
        """
        print(f"📱 Creating new {brand} {model}...")

        # Initialize instance variables
        self.brand = brand
        self.model = model
        self.storage_gb = storage_gb
        self.color = color
        self.battery_level = 100
        self.is_on = False
        self.installed_apps = ["Phone", "Messages", "Settings"]
        self.creation_time = datetime.datetime.now()

        # Generate unique phone ID
        SmartPhone.phone_count += 1
        self.phone_id = f"PHONE_{SmartPhone.phone_count:04d}"

        # Add to class tracking
        SmartPhone.all_phones.append(weakref.ref(self))

        print(f"✅ {self.get_full_name()} created successfully!")
        print(f"   ID: {self.phone_id}")
        print(f"   Storage: {self.storage_gb}GB")
        print(f"   Color: {self.color}")
        print(f"   Created at: {self.creation_time.strftime('%Y-%m-%d %H:%M:%S')}")

    def __del__(self):
        """
        Destructor - Cleanup when phone object is destroyed
        """
        print(f"🗑️ Destroying {self.get_full_name()}...")
        print(f"   Phone was active for: {self.get_lifetime()}")
        print(f"   Final battery level: {self.battery_level}%")
        print(f"   Apps installed: {len(self.installed_apps)}")
        print("   Clearing memory and releasing resources...")
        print(f"✅ {self.phone_id} destroyed successfully!")

    def get_full_name(self):
        return f"{self.brand} {self.model}"

    def get_lifetime(self):
        """Calculate how long the phone object has existed"""
        lifetime = datetime.datetime.now() - self.creation_time
        return str(lifetime).split('.')[0]  # Remove microseconds

    def power_on(self):
        if not self.is_on:
            self.is_on = True
            self.battery_level -= 1
            print(f"🔋 {self.get_full_name()} powered on!")
        else:
            print("📱 Phone is already on!")

    def power_off(self):
        if self.is_on:
            self.is_on = False
            print(f"😴 {self.get_full_name()} powered off!")
        else:
            print("📱 Phone is already off!")

    def install_app(self, app_name):
        if app_name not in self.installed_apps:
            self.installed_apps.append(app_name)
            self.battery_level -= 2
            print(f"📲 Installed {app_name}")
        else:
            print(f"⚠️ {app_name} is already installed")

    def use_phone(self, minutes):
        if self.is_on:
            battery_drain = minutes * 2
            self.battery_level = max(0, self.battery_level - battery_drain)
            print(f"📞 Used phone for {minutes} minutes. Battery: {self.battery_level}%")

            if self.battery_level == 0:
                self.power_off()
                print("🔋 Battery died! Phone powered off.")
        else:
            print("❌ Cannot use phone - it's powered off!")

    def charge(self, minutes):
        charge_amount = minutes * 5  # 5% per minute
        self.battery_level = min(100, self.battery_level + charge_amount)
        print(f"🔌 Charged for {minutes} minutes. Battery: {self.battery_level}%")

    def display_info(self):
        print(f"\n📱 === {self.get_full_name()} Info ===")
        print(f"ID: {self.phone_id}")
        print(f"Storage: {self.storage_gb}GB")
        print(f"Color: {self.color}")
        print(f"Battery: {self.battery_level}%")
        print(f"Status: {'ON' if self.is_on else 'OFF'}")
        print(f"Apps: {', '.join(self.installed_apps)}")
        print(f"Lifetime: {self.get_lifetime()}")
        print("=" * 30)

    @classmethod
    def get_active_phones(cls):
        """Get list of phones that still exist"""
        active = []
        for phone_ref in cls.all_phones:
            phone = phone_ref()  # Get object from weak reference
            if phone is not None:
                active.append(phone)
        return active

    @classmethod
    def create_iphone(cls, model="15", storage=128):
        """Factory method for creating iPhones"""
        return cls("Apple", f"iPhone {model}", storage, "Space Gray")

    @classmethod
    def create_android(cls, brand="Samsung", model="Galaxy S24", storage=256):
        """Factory method for creating Android phones"""
        return cls(brand, model, storage, "Phantom Black")

def phone_lifecycle_demo():
    """Demonstrate constructor and destructor behavior"""
    print("=== Phone Lifecycle Demo ===\n")

    # Constructor calls
    print("1. Creating phones...")
    iphone = SmartPhone.create_iphone("15 Pro", 512)
    android = SmartPhone.create_android("Google", "Pixel 8", 128)
    basic_phone = SmartPhone("Nokia", "3310", 16, "Blue")

    print(f"\nActive phones: {len(SmartPhone.get_active_phones())}")

    # Use the phones
    print("\n2. Using phones...")
    iphone.power_on()
    iphone.install_app("Instagram")
    iphone.install_app("TikTok")
    iphone.use_phone(30)

    android.power_on()
    android.install_app("YouTube")
    android.use_phone(45)
    android.charge(10)

    basic_phone.power_on()
    basic_phone.use_phone(120)  # Drain battery

    # Display info
    print("\n3. Phone status...")
    for phone in SmartPhone.get_active_phones():
        phone.display_info()

    # Demonstrate destructor
    print("\n4. Destroying phones...")
    print("Deleting iPhone...")
    del iphone  # Explicit destructor call

    print("\nDeleting Android...")
    del android

    print(f"\nActive phones remaining: {len(SmartPhone.get_active_phones())}")

    # basic_phone will be destroyed automatically when function ends
    print("\n5. Function ending - remaining phones will be destroyed...")

# Run the demo
if __name__ == "__main__":
    phone_lifecycle_demo()
    print("\n🏁 Demo completed - all objects destroyed!")
```

### Java Example

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    // Class variables
    private static int connectionCount = 0;
    private static List<DatabaseConnection> activeConnections = new ArrayList<>();

    // Instance variables
    private String connectionId;
    private String host;
    private int port;
    private String database;
    private String username;
    private boolean isConnected;
    private LocalDateTime createdAt;
    private int queryCount;
    private List<String> queryHistory;

    // Default Constructor
    public DatabaseConnection() {
        this("localhost", 5432, "defaultdb", "user");
        System.out.println("🔧 Default constructor called");
    }

    // Parameterized Constructor
    public DatabaseConnection(String host, int port, String database, String username) {
        System.out.println("🏗️ Creating database connection...");

        // Initialize instance variables
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.isConnected = false;
        this.createdAt = LocalDateTime.now();
        this.queryCount = 0;
        this.queryHistory = new ArrayList<>();

        // Generate unique connection ID
        connectionCount++;
        this.connectionId = String.format("CONN_%04d", connectionCount);

        // Add to active connections
        activeConnections.add(this);

        System.out.println("✅ Database connection created:");
        System.out.println("   ID: " + connectionId);
        System.out.println("   Host: " + host + ":" + port);
        System.out.println("   Database: " + database);
        System.out.println("   User: " + username);
        System.out.println("   Created: " + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Simulate connection setup
        initializeConnection();
    }

    // Copy Constructor (Java doesn't have built-in copy constructors)
    public DatabaseConnection(DatabaseConnection other) {
        this(other.host, other.port, other.database, other.username + "_copy");
        System.out.println("📋 Copy constructor called - cloned from " + other.connectionId);
    }

    // Initialization method called by constructor
    private void initializeConnection() {
        System.out.println("🔌 Initializing connection resources...");
        System.out.println("   Setting up SSL encryption...");
        System.out.println("   Configuring connection pool...");
        System.out.println("   Loading database drivers...");
        System.out.println("✅ Connection initialization complete!");
    }

    // Destructor equivalent - finalize() method (deprecated but shown for concept)
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("🗑️ Finalizing connection " + connectionId + "...");
            cleanup();
        } finally {
            super.finalize();
        }
    }

    // Explicit cleanup method (preferred over finalize)
    public void close() {
        System.out.println("🔒 Closing database connection " + connectionId + "...");
        cleanup();
    }

    private void cleanup() {
        if (isConnected) {
            disconnect();
        }

        System.out.println("🧹 Cleaning up connection resources...");
        System.out.println("   Closing network sockets...");
        System.out.println("   Releasing memory buffers...");
        System.out.println("   Clearing query cache...");
        System.out.println("   Total queries executed: " + queryCount);

        // Remove from active connections
        activeConnections.remove(this);

        System.out.println("✅ Connection " + connectionId + " cleanup complete!");
    }

    // Connection methods
    public boolean connect() {
        if (!isConnected) {
            System.out.println("🔗 Connecting to " + host + ":" + port + "/" + database + "...");
            System.out.println("   Authenticating user: " + username);
            System.out.println("   Establishing secure connection...");

            isConnected = true;
            System.out.println("✅ Connected successfully!");
            return true;
        } else {
            System.out.println("⚠️ Already connected!");
            return false;
        }
    }

    public void disconnect() {
        if (isConnected) {
            System.out.println("🔌 Disconnecting from database...");
            System.out.println("   Committing pending transactions...");
            System.out.println("   Closing prepared statements...");

            isConnected = false;
            System.out.println("✅ Disconnected successfully!");
        } else {
            System.out.println("⚠️ Already disconnected!");
        }
    }

    public void executeQuery(String query) {
        if (!isConnected) {
            System.out.println("❌ Cannot execute query - not connected!");
            return;
        }

        queryCount++;
        queryHistory.add(query);
        System.out.println("📊 Executing query #" + queryCount + ": " + query);
        System.out.println("   Query executed successfully!");
    }

    public void displayStatus() {
        System.out.println("\n📊 === Connection Status ===");
        System.out.println("ID: " + connectionId);
        System.out.println("Host: " + host + ":" + port);
        System.out.println("Database: " + database);
        System.out.println("User: " + username);
        System.out.println("Status: " + (isConnected ? "CONNECTED" : "DISCONNECTED"));
        System.out.println("Queries: " + queryCount);
        System.out.println("Created: " + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("==========================");
    }

    // Static methods
    public static int getConnectionCount() {
        return connectionCount;
    }

    public static List<DatabaseConnection> getActiveConnections() {
        return new ArrayList<>(activeConnections);
    }

    public static void displayAllConnections() {
        System.out.println("\n📋 === All Active Connections ===");
        System.out.println("Total connections created: " + connectionCount);
        System.out.println("Currently active: " + activeConnections.size());

        for (DatabaseConnection conn : activeConnections) {
            System.out.println("- " + conn.connectionId + " (" +
                             (conn.isConnected ? "CONNECTED" : "DISCONNECTED") + ")");
        }
        System.out.println("================================");
    }

    // Getters
    public String getConnectionId() { return connectionId; }
    public boolean isConnected() { return isConnected; }
    public int getQueryCount() { return queryCount; }
}

// Demo class
public class DatabaseDemo {
    public static void main(String[] args) {
        System.out.println("=== Database Connection Lifecycle Demo ===\n");

        // Constructor demonstrations
        System.out.println("1. Creating connections with different constructors...");

        // Default constructor
        DatabaseConnection defaultConn = new DatabaseConnection();

        // Parameterized constructor
        DatabaseConnection customConn = new DatabaseConnection(
            "production-db.company.com", 3306, "ecommerce", "admin"
        );

        // Copy constructor
        DatabaseConnection copyConn = new DatabaseConnection(customConn);

        DatabaseConnection.displayAllConnections();

        // Use the connections
        System.out.println("\n2. Using connections...");

        defaultConn.connect();
        defaultConn.executeQuery("SELECT * FROM users");
        defaultConn.executeQuery("SELECT COUNT(*) FROM orders");

        customConn.connect();
        customConn.executeQuery("SELECT * FROM products WHERE price > 100");
        customConn.executeQuery("UPDATE inventory SET quantity = quantity - 1");

        copyConn.connect();
        copyConn.executeQuery("SELECT * FROM logs WHERE date = TODAY()");

        // Display status
        System.out.println("\n3. Connection status...");
        defaultConn.displayStatus();
        customConn.displayStatus();
        copyConn.displayStatus();

        // Cleanup demonstration
        System.out.println("\n4. Cleaning up connections...");

        System.out.println("Explicitly closing default connection:");
        defaultConn.close();

        System.out.println("\nExplicitly closing custom connection:");
        customConn.close();

        DatabaseConnection.displayAllConnections();

        System.out.println("\n5. Creating temporary connection in scope...");
        {
            DatabaseConnection tempConn = new DatabaseConnection("temp-server", 5432, "tempdb", "temp_user");
            tempConn.connect();
            tempConn.executeQuery("SELECT 1");
            tempConn.displayStatus();

            // tempConn will be eligible for garbage collection when leaving this scope
            System.out.println("Leaving scope - tempConn becomes eligible for GC...");
        }

        // Force garbage collection (not guaranteed to run immediately)
        System.out.println("\n6. Suggesting garbage collection...");
        System.gc();

        try {
            Thread.sleep(1000); // Give GC a chance to run
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        DatabaseConnection.displayAllConnections();

        System.out.println("\n🏁 Demo completed!");
        System.out.println("Note: Remaining connections will be cleaned up when JVM exits.");
    }
}
```

### C++ Example - Complete Constructor/Destructor Demo

```cpp
#include <iostream>
#include <string>
#include <vector>
#include <memory>
#include <chrono>
#include <iomanip>

class FileManager {
private:
    std::string filename;
    std::string mode;
    bool isOpen;
    size_t bytesRead;
    size_t bytesWritten;
    std::chrono::time_point<std::chrono::steady_clock> createdAt;

    static int totalFiles;
    static std::vector<FileManager*> openFiles;

public:
    // Default Constructor
    FileManager() : FileManager("default.txt", "r") {
        std::cout << "🔧 Default constructor called" << std::endl;
    }

    // Parameterized Constructor
    FileManager(const std::string& fname, const std::string& fmode)
        : filename(fname), mode(fmode), isOpen(false),
          bytesRead(0), bytesWritten(0) {

        createdAt = std::chrono::steady_clock::now();
        totalFiles++;

        std::cout << "🏗️ Creating FileManager for: " << filename << std::endl;
        std::cout << "   Mode: " << mode << std::endl;
        std::cout << "   File #" << totalFiles << std::endl;

        // Simulate file system operations
        initializeFile();

        // Track open files
        openFiles.push_back(this);

        std::cout << "✅ FileManager created successfully!" << std::endl;
    }

    // Copy Constructor
    FileManager(const FileManager& other)
        : filename(other.filename + "_copy"), mode(other.mode),
          isOpen(false), bytesRead(0), bytesWritten(0) {

        createdAt = std::chrono::steady_clock::now();
        totalFiles++;

        std::cout << "📋 Copy constructor called" << std::endl;
        std::cout << "   Original: " << other.filename << std::endl;
        std::cout << "   Copy: " << filename << std::endl;

        initializeFile();
        openFiles.push_back(this);

        std::cout << "✅ Copy created successfully!" << std::endl;
    }

    // Move Constructor (C++11)
    FileManager(FileManager&& other) noexcept
        : filename(std::move(other.filename)), mode(std::move(other.mode)),
          isOpen(other.isOpen), bytesRead(other.bytesRead),
          bytesWritten(other.bytesWritten), createdAt(other.createdAt) {

        std::cout << "🚚 Move constructor called for: " << filename << std::endl;

        // Transfer ownership
        other.isOpen = false;
        other.bytesRead = 0;
        other.bytesWritten = 0;

        // Update tracking
        auto it = std::find(openFiles.begin(), openFiles.end(), &other);
        if (it != openFiles.end()) {
            *it = this;
        }

        std::cout << "✅ Move completed successfully!" << std::endl;
    }

    // Destructor
    ~FileManager() {
        std::cout << "🗑️ Destroying FileManager for: " << filename << std::endl;

        // Calculate lifetime
        auto now = std::chrono::steady_clock::now();
        auto lifetime = std::chrono::duration_cast<std::chrono::milliseconds>(now - createdAt);

        std::cout << "   Lifetime: " << lifetime.count() << "ms" << std::endl;
        std::cout << "   Bytes read: " << bytesRead << std::endl;
        std::cout << "   Bytes written: " << bytesWritten << std::endl;

        // Cleanup operations
        cleanup();

        // Remove from tracking
        auto it = std::find(openFiles.begin(), openFiles.end(), this);
        if (it != openFiles.end()) {
            openFiles.erase(it);
        }

        std::cout << "✅ " << filename << " destroyed successfully!" << std::endl;
    }

private:
    void initializeFile() {
        std::cout << "🔧 Initializing file operations..." << std::endl;
        std::cout << "   Checking file permissions..." << std::endl;
        std::cout << "   Allocating buffer memory..." << std::endl;
        std::cout << "   Setting up file handles..." << std::endl;
    }

    void cleanup() {
        if (isOpen) {
            close();
        }

        std::cout << "🧹 Cleaning up resources..." << std::endl;
        std::cout << "   Flushing buffers..." << std::endl;
        std::cout << "   Releasing memory..." << std::endl;
        std::cout << "   Closing file handles..." << std::endl;
    }

public:
    // File operations
    bool open() {
        if (!isOpen) {
            std::cout << "📂 Opening file: " << filename << " in mode: " << mode << std::endl;
            isOpen = true;
            return true;
        } else {
            std::cout << "⚠️ File already open!" << std::endl;
            return false;
        }
    }

    void close() {
        if (isOpen) {
            std::cout << "🔒 Closing file: " << filename << std::endl;
            std::cout << "   Saving pending changes..." << std::endl;
            std::cout << "   Updating file metadata..." << std::endl;
            isOpen = false;
        } else {
            std::cout << "⚠️ File already closed!" << std::endl;
        }
    }

    void read(size_t bytes) {
        if (isOpen && (mode == "r" || mode == "rw")) {
            bytesRead += bytes;
            std::cout << "📖 Read " << bytes << " bytes from " << filename
                      << " (Total: " << bytesRead << ")" << std::endl;
        } else {
            std::cout << "❌ Cannot read - file not open or wrong mode!" << std::endl;
        }
    }

    void write(size_t bytes) {
        if (isOpen && (mode == "w" || mode == "rw")) {
            bytesWritten += bytes;
            std::cout << "✏️ Wrote " << bytes << " bytes to " << filename
                      << " (Total: " << bytesWritten << ")" << std::endl;
        } else {
            std::cout << "❌ Cannot write - file not open or wrong mode!" << std::endl;
        }
    }

    void displayInfo() const {
        std::cout << "\n📄 === File Info ===" << std::endl;
        std::cout << "Filename: " << filename << std::endl;
        std::cout << "Mode: " << mode << std::endl;
        std::cout << "Status: " << (isOpen ? "OPEN" : "CLOSED") << std::endl;
        std::cout << "Bytes Read: " << bytesRead << std::endl;
        std::cout << "Bytes Written: " << bytesWritten << std::endl;

        auto now = std::chrono::steady_clock::now();
        auto lifetime = std::chrono::duration_cast<std::chrono::milliseconds>(now - createdAt);
        std::cout << "Lifetime: " << lifetime.count() << "ms" << std::endl;
        std::cout << "===================" << std::endl;
    }

    // Assignment operators
    FileManager& operator=(const FileManager& other) {
        if (this != &other) {
            std::cout << "📝 Copy assignment operator called" << std::endl;

            // Clean up current resources
            cleanup();

            // Copy from other
            filename = other.filename + "_assigned";
            mode = other.mode;
            isOpen = false;
            bytesRead = 0;
            bytesWritten = 0;
            createdAt = std::chrono::steady_clock::now();

            initializeFile();
        }
        return *this;
    }

    FileManager& operator=(FileManager&& other) noexcept {
        if (this != &other) {
            std::cout << "🚚 Move assignment operator called" << std::endl;

            // Clean up current resources
            cleanup();

            // Move from other
            filename = std::move(other.filename);
            mode = std::move(other.mode);
            isOpen = other.isOpen;
            bytesRead = other.bytesRead;
            bytesWritten = other.bytesWritten;
            createdAt = other.createdAt;

            // Reset other
            other.isOpen = false;
            other.bytesRead = 0;
            other.bytesWritten = 0;
        }
        return *this;
    }

    // Static methods
    static int getTotalFiles() { return totalFiles; }
    static size_t getOpenFilesCount() { return openFiles.size(); }

    static void displayAllFiles() {
        std::cout << "\n📊 === File Manager Statistics ===" << std::endl;
        std::cout << "Total files created: " << totalFiles << std::endl;
        std::cout << "Currently open files: " << openFiles.size() << std::endl;

        for (const auto* file : openFiles) {
            std::cout << "- " << file->filename << " ("
                      << (file->isOpen ? "OPEN" : "CLOSED") << ")" << std::endl;
        }
        std::cout << "=================================" << std::endl;
    }

    // Getters
    const std::string& getFilename() const { return filename; }
    bool getIsOpen() const { return isOpen; }
};

// Initialize static members
int FileManager::totalFiles = 0;
std::vector<FileManager*> FileManager::openFiles;

// Demonstration functions
void demonstrateConstructors() {
    std::cout << "\n=== Constructor Demonstrations ===\n" << std::endl;

    // Default constructor
    std::cout << "1. Default Constructor:" << std::endl;
    FileManager defaultFile;

    std::cout << "\n2. Parameterized Constructor:" << std::endl;
    FileManager configFile("config.ini", "rw");

    std::cout << "\n3. Copy Constructor:" << std::endl;
    FileManager copyFile = configFile;  // Copy constructor

    std::cout << "\n4. Move Constructor:" << std::endl;
    FileManager moveFile = std::move(FileManager("temp.dat", "w"));  // Move constructor

    FileManager::displayAllFiles();

    // Use the files
    std::cout << "\n=== Using Files ===" << std::endl;
    defaultFile.open();
    defaultFile.read(1024);

    configFile.open();
    configFile.write(512);
    configFile.read(256);

    copyFile.open();
    copyFile.write(128);

    moveFile.open();
    moveFile.write(2048);

    // Display info
    defaultFile.displayInfo();
    configFile.displayInfo();
    copyFile.displayInfo();
    moveFile.displayInfo();

    std::cout << "\n=== End of Scope - Destructors Will Be Called ===" << std::endl;
}

void demonstrateRAII() {
    std::cout << "\n=== RAII (Resource Acquisition Is Initialization) Demo ===\n" << std::endl;

    {
        std::cout << "Entering inner scope..." << std::endl;
        FileManager scopedFile("scoped.txt", "rw");
        scopedFile.open();
        scopedFile.write(1000);
        scopedFile.read(500);

        std::cout << "Leaving inner scope..." << std::endl;
        // Destructor automatically called here
    }

    std::cout << "Back in outer scope - scoped file was automatically cleaned up!" << std::endl;
}

void demonstrateSmartPointers() {
    std::cout << "\n=== Smart Pointers Demo ===\n" << std::endl;

    {
        std::cout << "Creating files with smart pointers..." << std::endl;

        auto uniqueFile = std::make_unique<FileManager>("unique.log", "w");
        auto sharedFile1 = std::make_shared<FileManager>("shared.db", "rw");
        auto sharedFile2 = sharedFile1;  // Shared ownership

        uniqueFile->open();
        uniqueFile->write(500);

        sharedFile1->open();
        sharedFile1->write(750);
        sharedFile2->read(250);  // Same object as sharedFile1

        FileManager::displayAllFiles();

        std::cout << "Leaving scope - smart pointers will clean up automatically..." << std::endl;
        // unique_ptr and shared_ptr automatically call destructors
    }

    std::cout << "Smart pointers cleaned up successfully!" << std::endl;
}

int main() {
    std::cout << "=== Constructor and Destructor Lifecycle Demo ===\n" << std::endl;

    demonstrateConstructors();

    std::cout << "\n" << std::string(60, '=') << std::endl;
    demonstrateRAII();

    std::cout << "\n" << std::string(60, '=') << std::endl;
    demonstrateSmartPointers();

    std::cout << "\n" << std::string(60, '=') << std::endl;
    FileManager::displayAllFiles();

    std::cout << "\n🏁 Main function ending - any remaining objects will be destroyed..." << std::endl;

    return 0;
}
```

## Key Concepts Explained

### 1. **Constructor Chaining**

```cpp
class Example {
public:
    Example() : Example(0, "default") {}  // Delegate to other constructor
    Example(int x) : Example(x, "partial") {}
    Example(int x, string s) { /* Main constructor */ }
};
```

### 2. **RAII (Resource Acquisition Is Initialization)**

```cpp
class FileHandle {
    FILE* file;
public:
    FileHandle(const char* name) : file(fopen(name, "r")) {}
    ~FileHandle() { if(file) fclose(file); }  // Automatic cleanup
};
```

### 3. **Rule of Three/Five (C++)**

If you need one, you probably need all:

- Destructor
- Copy constructor
- Copy assignment operator
- Move constructor (C++11)
- Move assignment operator (C++11)

## Best Practices

### 1. **Initialize in Constructor**

```python
class GoodClass:
    def __init__(self, value):
        self.value = value      # Always initialize
        self.items = []         # Don't use mutable defaults
        self.count = 0          # Initialize all attributes

class BadClass:
    def __init__(self, value):
        self.value = value      # Some attributes not initialized
        # self.count not set - could cause errors later
```

### 2. **Use Constructor Delegation**

```java
public class Person {
    public Person() {
        this("Unknown", 0);  // Delegate to main constructor
    }

    public Person(String name) {
        this(name, 0);       // Delegate to main constructor
    }

    public Person(String name, int age) {  // Main constructor
        this.name = name;
        this.age = age;
    }
}
```

### 3. **Proper Resource Management**

```cpp
class ResourceManager {
    int* data;
public:
    ResourceManager(size_t size) : data(new int[size]) {}
    ~ResourceManager() { delete[] data; }  // Always clean up

    // Prevent copying to avoid double-delete
    ResourceManager(const ResourceManager&) = delete;
    ResourceManager& operator=(const ResourceManager&) = delete;
};
```

### 4. **Exception Safety in Constructors**

```cpp
class SafeClass {
    Resource1* r1;
    Resource2* r2;
public:
    SafeClass() : r1(nullptr), r2(nullptr) {
        try {
            r1 = new Resource1();
            r2 = new Resource2();
        } catch (...) {
            delete r1;  // Clean up if constructor fails
            throw;
        }
    }

    ~SafeClass() {
        delete r2;
        delete r1;
    }
};
```

## Common Pitfalls

❌ **Forgetting to initialize variables:**

```python
class BadClass:
    def __init__(self, name):
        self.name = name
        # Forgot to initialize self.count - will cause AttributeError later

    def increment(self):
        self.count += 1  # Error! count not initialized
```

❌ **Not cleaning up resources:**

```python
class BadFileHandler:
    def __init__(self, filename):
        self.file = open(filename, 'r')
        # No __del__ method - file might not be closed
```

❌ **Circular references preventing cleanup:**

```python
class Parent:
    def __init__(self):
        self.children = []

    def add_child(self, child):
        self.children.append(child)
        child.parent = self  # Circular reference - use weak references instead
```

✅ **Proper cleanup with context managers:**

```python
class GoodFileHandler:
    def __init__(self, filename):
        self.filename = filename
        self.file = None

    def __enter__(self):
        self.file = open(self.filename, 'r')
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        if self.file:
            self.file.close()

# Usage
with GoodFileHandler("data.txt") as handler:
    # File automatically closed when leaving this block
    pass
```

## Summary

Constructors and destructors manage the complete lifecycle of objects - from birth to death. Constructors set up initial state and acquire resources, while destructors clean up and release resources. Proper use of these special methods is crucial for writing robust, memory-safe applications.

**Key Principles:**

- **Constructors**: Initialize everything, acquire resources safely
- **Destructors**: Clean up everything, release resources properly
- **RAII**: Tie resource lifetime to object lifetime
- **Exception Safety**: Handle failures gracefully during construction

Remember: Good constructor/destructor design prevents memory leaks, resource leaks, and undefined behavior! 🛡️
