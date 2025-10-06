
var longestCommonPrefix = function (strs) {
	if (strs.length === 0) return "";
	let prefix = strs[0].split("");
	strs.map((element) => {
		let newarr = element.split("");
		for (let i = 0; i < prefix.length; i++) {
			if (prefix[i] !== newarr[i]) {
				console.log((prefix = prefix.slice(0, i)));
				break;
			}
		}
	});

	return prefix.join("");
};

const strs = ["flower", "flow", "flight"];
longestCommonPrefix(strs);
