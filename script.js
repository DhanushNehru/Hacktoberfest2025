// Smooth scrolling for navigation links
document.querySelectorAll('nav a[href^="#"]').forEach(anchor => {
  anchor.addEventListener('click', function (e) {
    e.preventDefault();
    const targetId = this.getAttribute('href').substring(1);
    const target = document.getElementById(targetId);
    if (target) {
      window.scrollTo({
        top: target.offsetTop - 70,
        behavior: "smooth"
      });
    }
  });
});

// Highlight active section in navbar
const sections = document.querySelectorAll("section");
const navLinks = document.querySelectorAll("nav ul li a");

window.addEventListener("scroll", () => {
  let current = "";
  sections.forEach(section => {
    const sectionTop = section.offsetTop - 80;
    const sectionHeight = section.clientHeight;
    if (scrollY >= sectionTop && scrollY < sectionTop + sectionHeight) {
      current = section.getAttribute("id");
    }
  });
  
  navLinks.forEach(link => {
    link.classList.remove("text-blue-600", "font-semibold");
    if (link.getAttribute("href").includes(current)) {
      link.classList.add("text-blue-600", "font-semibold");
    }
  });
});

// Mobile menu toggle (for smaller screens)
const menuToggle = document.createElement("button");
menuToggle.innerHTML = "☰";
menuToggle.className = "md:hidden text-2xl text-blue-700 focus:outline-none";
const navBar = document.querySelector("nav");
navBar.insertBefore(menuToggle, navBar.firstChild);

const navMenu = document.querySelector("nav ul");
menuToggle.addEventListener("click", () => {
  navMenu.classList.toggle("hidden");
});

// Contact form (optional enhancement)
const contactButton = document.querySelector('a[href^="mailto:"]');
if (contactButton) {
  contactButton.addEventListener("click", (e) => {
    alert("Opening your mail app to send a message to CuriFix Hospital!");
  });
}

// Scroll-to-top button
const scrollTopBtn = document.createElement("button");
scrollTopBtn.innerHTML = "↑";
scrollTopBtn.className = "fixed bottom-6 right-6 bg-blue-600 text-white px-3 py-2 rounded-full shadow-lg hidden hover:bg-blue-700 transition";
document.body.appendChild(scrollTopBtn);

window.addEventListener("scroll", () => {
  if (window.scrollY > 300) {
    scrollTopBtn.classList.remove("hidden");
  } else {
    scrollTopBtn.classList.add("hidden");
  }
});

scrollTopBtn.addEventListener("click", () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
});
