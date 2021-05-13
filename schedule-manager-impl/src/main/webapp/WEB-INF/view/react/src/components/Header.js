import PropTypes from 'prop-types'

const Header = (props) => {
    return (
        <header>
            <h1>Schedule Manager</h1>
        </header>
    )
}

Header.defaultProps = {
    username: 'User',
}

Header.propTypes = {
    username: PropTypes.string.isRequired,
}

export default Header
