import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

/**
 * Comprehensive test suite for AnagramChecker
 * Tests all three implementations: HashMap, Sorting, and Optimized
 * 
 * @author Prince Yadav
 * @version 1.0
 */
@DisplayName("AnagramChecker Test Suite")
class AnagramCheckerTest {
    
    @Nested
    @DisplayName("HashMap Approach Tests")
    class HashMapApproachTests {
        
        @Test
        @DisplayName("Valid anagrams should return true")
        void testValidAnagrams() {
            assertTrue(AnagramChecker.isAnagram("listen", "silent"));
            assertTrue(AnagramChecker.isAnagram("evil", "vile"));
            assertTrue(AnagramChecker.isAnagram("a gentleman", "elegant man"));
            assertTrue(AnagramChecker.isAnagram("school master", "the classroom"));
            assertTrue(AnagramChecker.isAnagram("conversation", "voices rant on"));
        }
        
        @Test
        @DisplayName("Invalid anagrams should return false")
        void testInvalidAnagrams() {
            assertFalse(AnagramChecker.isAnagram("hello", "world"));
            assertFalse(AnagramChecker.isAnagram("listen", "hearing"));
            assertFalse(AnagramChecker.isAnagram("python", "java"));
        }
        
        @Test
        @DisplayName("Case insensitive comparison")
        void testCaseInsensitive() {
            assertTrue(AnagramChecker.isAnagram("Listen", "Silent"));
            assertTrue(AnagramChecker.isAnagram("EVIL", "vile"));
            assertTrue(AnagramChecker.isAnagram("Angel", "GLEAN"));
        }
        
        @Test
        @DisplayName("Handles special characters and spaces")
        void testSpecialCharacters() {
            assertTrue(AnagramChecker.isAnagram("a-gentleman!", "elegant man?"));
            assertTrue(AnagramChecker.isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort"));
            assertTrue(AnagramChecker.isAnagram("slot-machines", "cash lost in me"));
        }
        
        @ParameterizedTest
        @NullSource
        @DisplayName("Null inputs should return false")
        void testNullInputs(String nullStr) {
            assertFalse(AnagramChecker.isAnagram(nullStr, "test"));
            assertFalse(AnagramChecker.isAnagram("test", nullStr));
            assertFalse(AnagramChecker.isAnagram(nullStr, nullStr));
        }
    }
    
    @Nested
    @DisplayName("Sorting Approach Tests")
    class SortingApproachTests {
        
        @Test
        @DisplayName("Valid anagrams using sorting")
        void testValidAnagramsSorting() {
            assertTrue(AnagramChecker.isAnagramSorting("evil", "vile"));
            assertTrue(AnagramChecker.isAnagramSorting("angel", "glean"));
            assertTrue(AnagramChecker.isAnagramSorting("binary", "brainy"));
        }
        
        @Test
        @DisplayName("Invalid anagrams using sorting")
        void testInvalidAnagramsSorting() {
            assertFalse(AnagramChecker.isAnagramSorting("abc", "def"));
            assertFalse(AnagramChecker.isAnagramSorting("hello", "help"));
        }
        
        @ParameterizedTest
        @CsvSource({
            "listen, silent, true",
            "dormitory, dirty room, true",
            "astronomer, moon starer, true",
            "the eyes, they see, true",
            "abc, xyz, false"
        })
        @DisplayName("Parameterized sorting tests")
        void testParameterizedSorting(String word1, String word2, boolean expected) {
            assertEquals(expected, AnagramChecker.isAnagramSorting(word1, word2));
        }
    }
    
    @Nested
    @DisplayName("Optimized Approach Tests")
    class OptimizedApproachTests {
        
        @Test
        @DisplayName("Valid anagrams using optimized approach")
        void testValidAnagramsOptimized() {
            assertTrue(AnagramChecker.isAnagramOptimized("listen", "silent"));
            assertTrue(AnagramChecker.isAnagramOptimized("angel", "glean"));
            assertTrue(AnagramChecker.isAnagramOptimized("stressed", "desserts"));
        }
        
        @Test
        @DisplayName("Invalid anagrams using optimized approach")
        void testInvalidAnagramsOptimized() {
            assertFalse(AnagramChecker.isAnagramOptimized("abc", "xyz"));
            assertFalse(AnagramChecker.isAnagramOptimized("hello", "world"));
        }
        
        @Test
        @DisplayName("Long strings performance")
        void testLongStrings() {
            String long1 = "thequickbrownfoxjumpsoverthelazydog";
            String long2 = "dogzylaehtrevospmujxofnworbkciuqeht";
            assertTrue(AnagramChecker.isAnagramOptimized(long1, long2));
        }
    }
    
    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCaseTests {
        
        @Test
        @DisplayName("Empty strings should be anagrams")
        void testEmptyStrings() {
            assertTrue(AnagramChecker.isAnagram("", ""));
            assertTrue(AnagramChecker.isAnagramSorting("", ""));
            assertTrue(AnagramChecker.isAnagramOptimized("", ""));
        }
        
        @Test
        @DisplayName("Single character comparison")
        void testSingleCharacter() {
            assertTrue(AnagramChecker.isAnagram("a", "a"));
            assertFalse(AnagramChecker.isAnagram("a", "b"));
            assertTrue(AnagramChecker.isAnagramSorting("z", "z"));
        }
        
        @Test
        @DisplayName("Different length strings")
        void testDifferentLengths() {
            assertFalse(AnagramChecker.isAnagram("abc", "abcd"));
            assertFalse(AnagramChecker.isAnagramSorting("test", "testing"));
            assertFalse(AnagramChecker.isAnagramOptimized("short", "longer"));
        }
        
        @Test
        @DisplayName("Repeated characters")
        void testRepeatedCharacters() {
            assertTrue(AnagramChecker.isAnagram("aabbcc", "abcabc"));
            assertFalse(AnagramChecker.isAnagram("aab", "abb"));
            assertTrue(AnagramChecker.isAnagramOptimized("aaa", "aaa"));
        }
        
        @Test
        @DisplayName("Only spaces and special characters")
        void testOnlySpecialCharacters() {
            assertTrue(AnagramChecker.isAnagram("   ", "   "));
            assertTrue(AnagramChecker.isAnagram("!!!", "!!!"));
            assertTrue(AnagramChecker.isAnagram("@#$", "$#@"));
        }
    }
    
    @Nested
    @DisplayName("Cross-Method Consistency Tests")
    class ConsistencyTests {
        
        @ParameterizedTest
        @CsvSource({
            "listen, silent",
            "evil, vile",
            "a gentleman, elegant man",
            "school master, the classroom",
            "conversation, voices rant on"
        })
        @DisplayName("All methods should agree on valid anagrams")
        void testAllMethodsAgree(String word1, String word2) {
            boolean hashMapResult = AnagramChecker.isAnagram(word1, word2);
            boolean sortingResult = AnagramChecker.isAnagramSorting(word1, word2);
            boolean optimizedResult = AnagramChecker.isAnagramOptimized(word1, word2);
            
            assertEquals(hashMapResult, sortingResult, 
                "HashMap and Sorting results differ");
            assertEquals(sortingResult, optimizedResult, 
                "Sorting and Optimized results differ");
            assertEquals(hashMapResult, optimizedResult, 
                "HashMap and Optimized results differ");
        }
        
        @Test
        @DisplayName("All methods handle null consistently")
        void testNullConsistency() {
            assertFalse(AnagramChecker.isAnagram(null, "test"));
            assertFalse(AnagramChecker.isAnagramSorting(null, "test"));
            assertFalse(AnagramChecker.isAnagramOptimized(null, "test"));
        }
    }
    
    @Nested
    @DisplayName("Performance Boundary Tests")
    class PerformanceTests {
        
        @Test
        @DisplayName("Handle very long anagrams")
        void testVeryLongAnagrams() {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            
            for (int i = 0; i < 1000; i++) {
                sb1.append("abc");
                sb2.append("cab");
            }
            
            assertTrue(AnagramChecker.isAnagram(sb1.toString(), sb2.toString()));
        }
        
        @Test
        @DisplayName("Handle strings with many repeated characters")
        void testManyRepeatedChars() {
            String s1 = "a".repeat(1000) + "b".repeat(1000);
            String s2 = "b".repeat(1000) + "a".repeat(1000);
            
            assertTrue(AnagramChecker.isAnagramOptimized(s1, s2));
        }
    }
}