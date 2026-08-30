function reverseString(str) {
  return str.split('').reverse().join('');
}

console.log(reverseString('Hello1')); // 1olleH

////////////////////////////////////////

function reverseStringInPlace(str) {
  const chars = str.split('');
  let i = 0;
  let j = chars.length - 1;

  while (i < j) {
    const temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
    i++;
    j--;
  }

  return chars.join('');
}

console.log(reverseStringInPlace('Hello1')); // 1olleH

module.exports = reverseString;
