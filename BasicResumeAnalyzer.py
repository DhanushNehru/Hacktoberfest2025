# ml.py
import pdfplumber
import docx
import re
import spacy

nlp = spacy.load("en_core_web_sm")


SKILLS = ["Python", "Machine Learning", "NLP", "Deep Learning",
          "Data Analysis", "SQL", "TensorFlow", "PyTorch"]


# Resume Parsing
def extract_text(file):
    """Extract text from PDF or DOCX file"""
    if str(file).endswith(".pdf"):
        text = ""
        with pdfplumber.open(file) as pdf:
            for page in pdf.pages:
                text += page.extract_text() + "\n"
        return text
    elif str(file).endswith(".docx"):
        doc = docx.Document(file)
        return "\n".join([para.text for para in doc.paragraphs])
    else:
        return ""

# Skills Extraction

def extract_skills(text):
    """Find skills from predefined SKILLS list in resume text"""
    skills_found = []
    for skill in SKILLS:
        if re.search(r'\b' + re.escape(skill) + r'\b', text, re.I):
            skills_found.append(skill)
    return skills_found

# Experience Extraction

def extract_experience(text):
    """Detect years of experience from resume text"""
    exp = re.findall(r'(\d+)\s+year', text, re.I)
    if exp:
        return max([int(x) for x in exp])
    else:
        return 0


# Resume Scoring

def score_resume(skills_found, experience):
    """Compute a simple resume score"""
    skill_score = len(skills_found) / len(SKILLS)  # 0 to 1
    exp_score = min(experience / 5, 1)            # max 1 for 5+ years
    total_score = round((skill_score + exp_score) / 2 * 100, 2)
    return total_score
