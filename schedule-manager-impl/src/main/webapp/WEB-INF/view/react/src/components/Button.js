import PropTypes from 'prop-types'

const Button = ({ color, text, onClick, type }) => {
  return (
    <button
      onClick={onClick}
      style={{ backgroundColor: color }}
      className='btn btn-block'
      type={type}
    >
      {text}
    </button>
  )
}

Button.defaultProps = {
  color: 'steelblue',
}

Button.propTypes = {
  text: PropTypes.string,
  color: PropTypes.string,
  onClick: PropTypes.func,
}

export default Button