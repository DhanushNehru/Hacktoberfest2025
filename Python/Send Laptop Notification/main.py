#pip install plyer
from plyer import notification

# Displaying the notification
notification.notify(
    title="Jan Pakhi",
    message="I Love You!",
    app_name="My Python App",
    timeout=10  # Duration in seconds
)