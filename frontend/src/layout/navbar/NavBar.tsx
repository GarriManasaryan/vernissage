import { Button, Checkbox, CheckboxProps, Input } from 'antd';
import { Breadcrumb, Layout, Menu } from 'antd';
import React from 'react'
import { Link } from 'react-router-dom'
import logo from '../navbar/vern_logo_fin(4).png'
import './styles.css'
import axiosConf from '../../axiosConf';

function NavBar() {

    const { Search } = Input;

    const onChange: CheckboxProps['onChange'] = (e) => {
        console.log(`checked = ${e.target.checked}`);
    };


    const testApi = () => {
        axiosConf.get(`/api/products`)
        .then((response)=>
            console.log(response.data)
        )
    }



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
                {/* <button className="btn-catalog">Catalog</button> */}
                <button 
                    // type="primary"
                    className="btn-catalog"
                    onClick={() => testApi()}
                >Catalog</button>
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