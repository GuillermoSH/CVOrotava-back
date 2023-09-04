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
        "nunito": ['Nunito', 'sans-serif'],
      },
      colors: {
        "main": "#34285a",
      },
      keyframes: {
        ballSlideInFromRight: {
          '0%': { transform: 'translateX(130%) rotate(180deg)', opacity: 0},
          '100%': { transform: 'translateX(0) rotate(0deg)', opacity: 1}
        },
        popup: {
          '0%': { transform: 'scale(0.25)', opacity: 0 },
          '100%': { transform: 'scale(1)', opacity: 1 }
        },
        dropdown: {
          '0%': { transform: 'translateY(-50%) scaleY(0)', transformOrigin: "top" , opacity: 0 },
          '100%': { transform: 'translateY(0) scaleY(1)', opacity: 1 }
        },
      },
      animation: {
        ballSlideInFromRight: 'ballSlideInFromRight 1s ease-out',
        popup: 'popup 0.3s ease-out',
        dropdown: 'dropdown 0.2s linear',
      }
    },
  },
  plugins: [],
}

