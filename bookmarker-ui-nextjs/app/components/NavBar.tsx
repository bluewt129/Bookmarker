import Image from "next/image";
import Link from "next/link";
export default function Navbar() {
    return (
        <header>
            <nav className={"navbar navbar-expand-lg bg-success"}>
                <div className={"container-fluid"}>
                <Link href="/" className="navbar-brand  text-white">NavBar</Link>
                    <button
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarNav"
                        className={"navbar-toggler"}
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span className={"navbar-toggler-icon"}></span>
                    </button>
                    <div className={"collapse navbar-collapse"} id="navbarNav">
                        <ul className={"navbar-nav ms-auto"}>
                            <li className={"nav-item"}>
                            <Link href="/bookmarks/add" className="nav-link active  text-white">Add Bookmark</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
}