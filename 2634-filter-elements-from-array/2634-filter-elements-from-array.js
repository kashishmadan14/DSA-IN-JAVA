/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var filter = function(arr, fn) {
    const filteredArr = [];
    
    for (let i = 0; i < arr.length; i++) {
        // Check if the callback function returns a truthy value
        if (fn(arr[i], i)) {
            filteredArr.push(arr[i]);
        }
    }
    
    return filteredArr;
};
