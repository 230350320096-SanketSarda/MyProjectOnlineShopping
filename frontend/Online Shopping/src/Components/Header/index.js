import { useNavigate } from 'react-router';
import './index.css'
import logo from './hblogo.png'
import { Link } from 'react-router-dom'

const Header = () => {

    const navigate = useNavigate()

    const logout = () => {
        sessionStorage.clear()
        navigate("/")
    }

    return (
        <div className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 py-md-4 mb-3 bg-warning border-bottom shadow-sm">
            <div className="logo-container">
                <img className="logo" src={logo} alt="logo" />
            </div>
            <h5 className="my-0 mr-md-auto  font-weight-normal">Online Shopping</h5>
            {(sessionStorage["personType"] === "customer" && sessionStorage["loginStatus"] === "1") ? 
                <div className='d-flex align-items-center'>
                    <nav className="my-2 my-md-0 mr-md-3">
                        <Link className="p-2 text-dark" to="/customer/home">Home</Link>
                        <Link className="p-2 text-dark" to="/customer/orders">All Orders</Link>
                        <Link className="p-2 text-dark" to="/customer/cart">Cart</Link>
                    </nav>
                    <a className="btn btn-outline-primary" href="#" onClick={() => logout()}>Log Out</a>
                </div>
                : <div></div>
            }
            {(sessionStorage["personType"] === "manager" && sessionStorage["loginStatus"] === "1") ? 
                <div className='d-flex align-items-center'>
                    <nav className="my-2 my-md-0 mr-md-3">
                        <Link className="p-2 text-dark" to="/manager/home">Home</Link>
                        <Link className="p-2 text-dark" to="/manager/restaurantmenu">Restaurant Menu</Link>
                        <Link className="p-2 text-dark" to="/manager/allorders">All Orders</Link>
                    </nav>
                    <a className="btn btn-outline-primary" href="#" onClick={() => logout() }>Log Out</a>
                </div>
                : <div></div>
            }
            {(sessionStorage["personType"] === "deliveryPerson" && sessionStorage["loginStatus"] === "1") ? 
                <div className='d-flex align-items-center'>
                    <nav className="my-2 my-md-0 mr-md-3">
                        <Link className="p-2 text-dark" to="/dp/home">Home</Link>
                        <Link className="p-2 text-dark" to="/dp/allorders">All Orders</Link>
                    </nav>
                    <a className="btn btn-outline-primary" href="#" onClick={() => logout() }>Log Out</a>
                </div>
                : <div></div>
            }
        </div>

    )
}

export default Header