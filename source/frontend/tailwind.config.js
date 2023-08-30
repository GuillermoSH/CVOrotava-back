/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
    "./src/**/**/*.{html,ts}",
    "./src/**/**/**/*.{html,ts}",
    "./src/**/**/**/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      fontFamily: {
        poppins: ['Poppins', 'sans-serif'],
      },
      colors: {
        main: "#34285a",
      },
      keyframes: {
        ballSlideInFromRight: {
          '0%': { transform: 'translateX(130%) rotate(180deg)', opacity: 0},
          '100%': { transform: 'translateX(0) rotate(0deg)', opacity: 1}
        }
      },
      animation: {
        ballSlideInFromRight: 'ballSlideInFromRight 1s ease-out'
      }
    },
  },
  plugins: [],
}

