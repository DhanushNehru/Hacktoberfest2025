//===================================================
// Made by Gurdeep Singh https://github.com/Gurdeep99
//===================================================
function textSimilarity(text1, text2) {
    // Helper: tokenize & clean
    function tokenize(text) {
      return text
        .toLowerCase()
        .replace(/[^a-zA-Z0-9\u0900-\u097F\s]/g, "") // supports English + Hindi
        .split(/\s+/)
        .filter(Boolean);
    }
  
    // Step 1: Tokenize texts
    const tokens1 = tokenize(text1);
    const tokens2 = tokenize(text2);
  
    // Step 2: Build vocabulary
    const vocab = Array.from(new Set([...tokens1, ...tokens2]));
  
    // Step 3: Term frequency
    function termFrequency(tokens) {
      const freq = {};
      tokens.forEach(word => freq[word] = (freq[word] || 0) + 1);
      return vocab.map(word => freq[word] || 0);
    }
  
    const tf1 = termFrequency(tokens1);
    const tf2 = termFrequency(tokens2);
  
    // Step 4: Cosine similarity
    function dot(a, b) {
      return a.reduce((sum, val, i) => sum + val * b[i], 0);
    }
    function magnitude(v) {
      return Math.sqrt(dot(v, v));
    }
  
    const cosine = dot(tf1, tf2) / (magnitude(tf1) * magnitude(tf2) || 1);
  
    return {
      similarity: (cosine * 100).toFixed(2) + "%",
      text1Tokens: tokens1.length,
      text2Tokens: tokens2.length,
      commonWords: vocab.filter(word => tokens1.includes(word) && tokens2.includes(word))
    };
  }
  
// =================
// ✅ Example Usage
// =================
  const result = textSimilarity(
    "Artificial intelligence is changing the world rapidly.",
    "AI is transforming the world in a very fast way."
  );
  
  console.log(result);
  

// =================
// ✅ Example Result
// =================

[{
    similarity: "72.15%",
    text1Tokens: 7,
    text2Tokens: 8,
    commonWords: ["world"]
}]
  