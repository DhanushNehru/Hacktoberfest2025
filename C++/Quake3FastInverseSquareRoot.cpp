#include <iostream>
#include <chrono>
#include <random>

/*
    Back in 1999, id Software released Quake III Arena.  
    Its lead programmer, John Carmack, needed every ounce of speed 
    from the hardware of the day.  

    One bottleneck was vector normalization, which requires 1/sqrt(x).  
    The normal sqrt was too slow, so the now-legendary 
    "Fast Inverse Square Root" hack was born.  

    The trick: reinterpret float bits as an integer, apply a magic constant 
    (0x5f3759df), and refine the result with Newton–Raphson.  
    The outcome: an approximation of 1/sqrt(x) that was *much* faster 
    on late 90s CPUs.  

    This single function helped Quake III run smoother,  
    and became one of the most famous pieces of game engine code ever written.  
    Today, with modern CPUs, it's no longer faster than std::sqrt 
    but it lives on as iconic example of code optimization.

    Inspired by this yt vid: https://www.youtube.com/watch?v=p8u_k2LIZyo
*/

float Q_rsqrt(float number) {
    long i;
    float x2, y;
    const float threehalfs = 1.5F;

    x2 = number * 0.5F;
    y = number;

    // Step 1 & 2: bit-level reinterpret of float as long
    i = *(long *)&y;  

    // Step 3: initial approximation using the magic constant
    i = 0x5f3759df - (i >> 1);

    // Step 4: convert bits back to float
    y = *(float *)&i;

    // Step 5: one Newton–Raphson iteration
    y = y * (threehalfs - (x2 * y * y));

    return y;
}

int main() {
    const int iterations = 10000000;
    volatile float result;             // prevent compiler optimization

    std::mt19937 rng(42); // fixed seed for reproducibility
    std::uniform_real_distribution<float> dist(1.0f, 1000.0f);

    // Benchmark Q_rsqrt
    auto start = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < iterations; i++) {
        float num = dist(rng);
        result = Q_rsqrt(num);
    }
    auto end = std::chrono::high_resolution_clock::now();

    auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count();
    std::cout << "Fast inverse square root last result: " << result << "\n";
    std::cout << "Q_rsqrt time: " << duration << " ms for " << iterations << " iterations\n";

    double avg = (duration * 1e6) / iterations;
    std::cout << "Average per call: " << avg << " ns\n";

    return 0;
}
