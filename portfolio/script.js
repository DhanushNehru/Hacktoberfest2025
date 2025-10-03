// Smooth scrolling for nav links
document.addEventListener('click', (e) => {
  const link = e.target.closest('a[href^="#"]');
  if (!link) return;
  const href = link.getAttribute('href');
  if (href.length > 1) {
    const target = document.querySelector(href);
    if (target) {
      e.preventDefault();
      target.scrollIntoView({ behavior: 'smooth', block: 'start' });
      const menu = document.getElementById('navMenu');
      if (menu && menu.classList.contains('is-open')) toggleMenu(false);
    }
  }
});

// Mobile nav toggle
const navToggle = document.querySelector('.nav__toggle');
const navMenu = document.getElementById('navMenu');
function toggleMenu(forceState) {
  const willOpen = typeof forceState === 'boolean' ? forceState : !navMenu.classList.contains('is-open');
  navMenu.classList.toggle('is-open', willOpen);
  navToggle.setAttribute('aria-expanded', String(willOpen));
}
if (navToggle && navMenu) {
  navToggle.addEventListener('click', () => toggleMenu());
}

// Typewriter effect in hero
(function typewriter() {
  const phrases = [
    'Cybersecurity Aspirant',
    'Ethical Hacker',
    'Security Researcher',
    'CSE Student',
    'MERN Stack Developer',
    'DevOps Enthusiast'
  ];
  const txtEl = document.querySelector('.typewriter__text');
  if (!txtEl) return;
  let i = 0, char = 0, deleting = false;
  const type = () => {
    const phrase = phrases[i % phrases.length];
    if (!deleting) {
      char++;
      txtEl.textContent = phrase.slice(0, char);
      if (char === phrase.length) {
        deleting = true;
        setTimeout(type, 1200);
        return;
      }
    } else {
      char--;
      txtEl.textContent = phrase.slice(0, char);
      if (char === 0) {
        deleting = false;
        i++;
      }
    }
    const delay = deleting ? 45 : 90;
    setTimeout(type, delay);
  };
  setTimeout(type, 600);
})();

// Scroll reveal animations
function initScrollReveal() {
  const animated = Array.from(document.querySelectorAll('[data-animate]'));
  if (animated.length) {
    const io = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add('is-visible');
          io.unobserve(entry.target);
        }
      });
    }, { threshold: 0.12 });
    animated.forEach((el) => io.observe(el));
  }
}

// Initialize scroll reveal on page load
initScrollReveal();

// Optimized 3D tilt for project cards
function initTiltEffects() {
  const tiltCards = Array.from(document.querySelectorAll('[data-tilt]'));
  tiltCards.forEach((card) => {
    const inner = card.querySelector('.project-card__inner') || card;
    let frame, isHovering = false;
    
    const onMove = (e) => {
      if (!isHovering) return;
      cancelAnimationFrame(frame);
      frame = requestAnimationFrame(() => {
        const rect = card.getBoundingClientRect();
        const dx = e.clientX - rect.left;
        const dy = e.clientY - rect.top;
        const px = (dx / rect.width) - 0.5;
        const py = (dy / rect.height) - 0.5;
        const rx = -py * 3; // Reduced rotation
        const ry = px * 5;  // Reduced rotation
        card.style.transform = `perspective(1000px) rotateX(${rx}deg) rotateY(${ry}deg) translateZ(3px)`;
      });
    };
    
    const onEnter = () => { isHovering = true; };
    const onLeave = () => {
      isHovering = false;
      cancelAnimationFrame(frame);
      card.style.transform = '';
    };
    
    inner.addEventListener('mouseenter', onEnter);
    inner.addEventListener('mousemove', onMove);
    inner.addEventListener('mouseleave', onLeave);
  });
}

// Initialize tilt effects on page load
initTiltEffects();

// Hero particles (optimized lightweight animation)
(function particles() {
  const canvas = document.getElementById('heroParticles');
  if (!canvas) return;
  const ctx = canvas.getContext('2d');
  let width, height, dpr, animationId;

  const PARTICLE_COUNT = 30; // Reduced from 80
  const particles = [];
  const rand = (min, max) => Math.random() * (max - min) + min;

  function resize() {
    dpr = 1; // Fixed DPR for better performance
    width = canvas.clientWidth;
    height = canvas.clientHeight;
    canvas.width = width;
    canvas.height = height;
  }

  function init() {
    particles.length = 0;
    for (let i = 0; i < PARTICLE_COUNT; i++) {
      particles.push({
        x: rand(0, width),
        y: rand(0, height),
        vx: rand(-0.2, 0.2), // Slower movement
        vy: rand(-0.2, 0.2),
        r: rand(0.8, 1.5), // Smaller particles
        a: rand(0.3, 0.6) // Less opacity
      });
    }
  }

  function step() {
    ctx.clearRect(0, 0, width, height);
    
    // Update particles
    for (let i = 0; i < particles.length; i++) {
      const p = particles[i];
      p.x += p.vx; p.y += p.vy;
      if (p.x < -20) p.x = width + 20; else if (p.x > width + 20) p.x = -20;
      if (p.y < -20) p.y = height + 20; else if (p.y > height + 20) p.y = -20;
    }

    // Draw particles only (no connection lines for performance)
    ctx.save();
    for (let i = 0; i < particles.length; i++) {
      const p = particles[i];
      ctx.beginPath();
      ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2);
      ctx.fillStyle = `rgba(0,246,255,${p.a})`;
      ctx.fill();
    }
    ctx.restore();
    
    animationId = requestAnimationFrame(step);
  }

  // Throttled resize
  let resizeTimeout;
  const onResize = () => {
    clearTimeout(resizeTimeout);
    resizeTimeout = setTimeout(() => {
      resize();
      init();
    }, 100);
  };

  // Initialize
  resize();
  init();
  step();

  // Event listeners
  window.addEventListener('resize', onResize);
  
  // Cleanup on page unload
  window.addEventListener('beforeunload', () => {
    if (animationId) cancelAnimationFrame(animationId);
  });
})();

// Footer year
const yearEl = document.getElementById('currentYear');
if (yearEl) yearEl.textContent = String(new Date().getFullYear());

// Fetch GitHub repositories with loading state
async function fetchGitHubRepos() {
  const username = 'mohammedroshant';
  const projectsGrid = document.querySelector('.projects__grid');
  
  if (!projectsGrid) return;
  
  // Show loading state
  projectsGrid.innerHTML = '<div class="loading">Loading projects...</div>';
  
  try {
    const response = await fetch(`https://api.github.com/users/${username}/repos?sort=updated&per_page=6`, {
      headers: {
        'Accept': 'application/vnd.github.v3+json'
      }
    });
    
    if (!response.ok) throw new Error(`GitHub API error: ${response.status}`);
    
    const repos = await response.json();
    const filteredRepos = repos.filter(repo => 
      !repo.fork && 
      repo.name !== 'mohammedroshant' &&
      repo.name !== 'README' &&
      repo.name !== 'profile' &&
      repo.name !== 'portfolio'
    );
    
    // Clear loading state
    projectsGrid.innerHTML = '';
    
    // Create project cards for each repo
    filteredRepos.slice(0, 4).forEach((repo, index) => {
      const card = createProjectCard(repo, index);
      projectsGrid.appendChild(card);
    });
    
    // Reinitialize animations and tilt effects for new cards
    setTimeout(() => {
      initScrollReveal();
      initTiltEffects();
    }, 100);
    
  } catch (error) {
    console.error('Error fetching GitHub repos:', error);
    // Show fallback content
    projectsGrid.innerHTML = `
      <div class="project-card" data-tilt data-animate="fade-up">
        <div class="project-card__inner">
          <h3 class="project-card__title">GitHub Projects</h3>
          <p class="project-card__desc">Unable to load projects from GitHub. Please check my <a href="https://github.com/mohammedroshant" target="_blank" rel="noopener noreferrer">GitHub profile</a> directly.</p>
          <div class="project-card__links">
            <a class="btn btn--ghost" href="https://github.com/mohammedroshant" target="_blank" rel="noopener noreferrer">View GitHub</a>
          </div>
        </div>
      </div>
    `;
    initScrollReveal();
    initTiltEffects();
  }
}

function createProjectCard(repo, index) {
  const card = document.createElement('article');
  card.className = 'project-card';
  card.setAttribute('data-tilt', '');
  card.setAttribute('data-animate', 'fade-up');
  
  // Get language icon
  const languageIcon = getLanguageIcon(repo.language);
  
  // Create tech tags from language and topics
  const techTags = [repo.language, ...(repo.topics || [])].filter(Boolean).slice(0, 4);
  
  // Create a better description if none exists
  const description = repo.description || 
    (repo.language ? `A ${repo.language} project showcasing modern development practices.` : 
     'A project showcasing modern development practices.');
  
  card.innerHTML = `
    <div class="project-card__inner">
      <h3 class="project-card__title">${repo.name.replace(/-/g, ' ').replace(/\b\w/g, l => l.toUpperCase())}</h3>
      <p class="project-card__desc">${description}</p>
      <ul class="project-card__tags" role="list">
        ${techTags.map(tag => `<li>${tag}</li>`).join('')}
      </ul>
      <div class="project-card__links">
        <a class="btn btn--ghost" href="${repo.html_url}" target="_blank" rel="noopener noreferrer">GitHub</a>
        <a class="btn btn--secondary" href="${repo.homepage || '#'}" target="_blank" rel="noopener noreferrer">Live Demo</a>
      </div>
    </div>
  `;
  
  return card;
}

function getLanguageIcon(language) {
  const icons = {
    'JavaScript': 'fab fa-js',
    'TypeScript': 'fab fa-js',
    'React': 'fab fa-react',
    'Node.js': 'fab fa-node-js',
    'HTML': 'fab fa-html5',
    'CSS': 'fab fa-css3-alt',
    'Python': 'fab fa-python',
    'Java': 'fab fa-java',
    'PHP': 'fab fa-php',
    'C++': 'fas fa-code',
    'C#': 'fas fa-code',
    'Go': 'fab fa-golang',
    'Rust': 'fas fa-code',
    'Ruby': 'fab fa-ruby',
    'Swift': 'fab fa-swift',
    'Kotlin': 'fas fa-code',
    'Dart': 'fas fa-code',
    'Vue': 'fab fa-vuejs',
    'Angular': 'fab fa-angular',
    'Svelte': 'fas fa-code'
  };
  return icons[language] || 'fas fa-code';
}

// Initialize GitHub repos on page load
document.addEventListener('DOMContentLoaded', () => {
  fetchGitHubRepos();
});



