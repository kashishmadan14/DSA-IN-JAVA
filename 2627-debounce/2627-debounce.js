/**
 * Creates a debounced function that delays invoking `func` until after 
 * `wait` milliseconds have elapsed since the last time it was invoked.
 */
function debounce(func, wait) {
  let timeoutId = null;

  return function (...args) {
    // Clear the previous timer if the function is called again before the timeout expires
    clearTimeout(timeoutId);

    // Set a new timer
    timeoutId = setTimeout(() => {
      // Invoke func with the correct 'this' context and arguments
      func.apply(this, args);
    }, wait);
  };
}
