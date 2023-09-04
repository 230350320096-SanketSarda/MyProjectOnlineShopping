import { Link } from "react-router-dom"

const Home = () => {
    return (
        <div>
            <h1 className="mt-3 mb-5">Welcome to Online shopping <br/>  Login / Register </h1>
            <table className="table">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Login</th>
                        <th>Register</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Customer</td>
                        <td>
                            <Link to={"/customer/signin"}>
                                <button type="button" className="btn btn-primary">Login</button>
                            </Link>
                        </td>
                        <td>
                            <Link to={"/customer/signup"}>
                                <button type="button" className="btn btn-primary">Register</button>
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <td>ADMIN</td>
                        <td>
                            <Link to={"/manager/signin"}>
                                <button type="button" className="btn btn-primary">Login</button>
                            </Link>
                        </td>
                        <td>
                            <Link to={"/manager/signup"}>
                                <button type="button" className="btn btn-primary">Register</button>
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <td>Delivery Person</td>
                        <td>
                          <Link to={"/dp/signin"}>
                            <button type="button" className="btn btn-primary">Login</button>
                            </Link>
                        </td>
                        <td>
                          <Link to={"/deliveryperson/signup"}>
                            <button type="button" className="btn btn-primary">Register</button>
                            </Link>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default Home