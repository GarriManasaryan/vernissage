import { Button, Checkbox, CheckboxProps, Input } from 'antd';
import { Breadcrumb, Layout, Menu } from 'antd';
import React from 'react'
import { Link } from 'react-router-dom'
import logo from '../navbar/vern_logo_fin(4).png'
import './styles.css'

function NavBar() {

    const { Search } = Input;

    const onChange: CheckboxProps['onChange'] = (e) => {
        console.log(`checked = ${e.target.checked}`);
    };


    return (
        <>
            <div
                className='navbar-main'
            >
                <Link
                    style={{ padding: '0 0 0.5rem 25rem' }}
                    className="logo-link" to='/image'
                >
                    <img className="logo" src={logo} alt="logo" />
                </Link>
                {/* <button className="bn632-hover bn19">Catalog</button> */}
                <button className="btn-catalog">Catalog</button>
                {/* <button className="btn-catalogv2">Catalog</button> */}
                {/* <img className="loho-catalog" src={catalog}></img> */}
                <Search
                    style={{
                        width: '550px',
                        fontSize: '20px',
                        paddingLeft: '2rem',
                    }}
                    size='middle'
                    //   onSearch={onSearch}
                    placeholder="Search"
                    //   addonBefore={searchOptions}
                    className='addonClass'
                    // addonAfter={<SearchOutlined/>}
                    allowClear
                // defaultValue="mysite" 
                />
            </div>
            <Layout
                style={{ padding: '0 0 0 0' }}

            ></Layout>
        </>
    )
}

export default NavBar