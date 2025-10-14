function twoSum(nums, target) {
  const seen = new Map(); // value -> index
  for (let i = 0; i < nums.length; i++) {
    const x = nums[i];
    const need = target - x;
    if (seen.has(need)) {
      return [seen.get(need), i];
    }
    seen.set(x, i);
  }
  throw new Error("No valid pair found");
}

function parseCLI(argv) {
  if (argv.length <= 2) {
    return { nums: [2, 7, 11, 15], target: 9 };
  }
  if (argv.length !== 4) {
    console.log('Usage: node two_sum_hashmap.js "<space-separated-ints>" <target>');
    process.exit(1);
  }
  const nums = argv[2]
    .trim()
    .split(/\s+/)
    .map((s) => Number.parseInt(s, 10));
  const target = Number.parseInt(argv[3], 10);
  return { nums, target };
}

function selfTest() {
  const asSet = (pair) => new Set(pair);
  const eqPairUnordered = (a, b) =>
    a[0] === b[0] && a[1] === b[1] || a[0] === b[1] && a[1] === b[0];

  if (!eqPairUnordered(twoSum([2, 7, 11, 15], 9), [0, 1])) throw new Error("T1");
  if (!eqPairUnordered(twoSum([3, 2, 4], 6), [1, 2])) throw new Error("T2");
  if (!eqPairUnordered(twoSum([3, 3], 6), [0, 1])) throw new Error("T3");
  if (!eqPairUnordered(twoSum([0, 4, 3, 0], 0), [0, 3])) throw new Error("T4");
  if (!eqPairUnordered(twoSum([-1, -2, -3, -4, -5], -8), [2, 4])) throw new Error("T5");
  console.log("All tests passed");
}

if (require.main === module) {
  selfTest();
  const { nums, target } = parseCLI(process.argv);
  const [i, j] = twoSum(nums, target);
  console.log(
    `nums=${JSON.stringify(nums)}, target=${target} -> indices=(${i}, ${j}), values=(${nums[i]}, ${nums[j]})`
  );
}

module.exports = { twoSum };
