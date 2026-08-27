import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [company, setCompany] = useState('')
  const [role, setRole] = useState('')
  const [siteLink, setSiteLink] = useState('')
  const [applications, setApplications] = useState([])

  useEffect(() => {
    fetch('/api/applications')
      .then((response) => {
        if (!response.ok) throw new Error('API unavailable')
        return response.json()
      })
      .then((data) => setApplications(data))
      .catch(() => setApplications([]))
  }, [])

  function addApplication(event) {
    event.preventDefault()
    if (!company.trim() || !role.trim() || !siteLink.trim()) return

    setApplications((current) => [
      { company: company.trim(), role: role.trim(), siteLink: siteLink.trim(), status: 'Applied' },
      ...current,
    ])
    setCompany('')
    setRole('')
    setSiteLink('')
  }

  return (
    <main className="app-shell">
      <header className="topbar">
        <div className="brand"><span className="brand-mark">JT</span><span>Jobtrack</span></div>
      </header>

      <section className="intro">
        <p className="eyebrow">Your search, in focus</p>
        <h1>Keep every opportunity moving.</h1>
        <p className="lede">A calm workspace for the applications you are chasing, waiting on, and winning.</p>
      </section>

      <section className="workspace">
        <form className="add-panel" onSubmit={addApplication}>
          <div className="panel-heading"><span className="panel-number">01</span><h2>Add an application</h2></div>
          <label>Company<input value={company} onChange={(event) => setCompany(event.target.value)} placeholder="e.g. Northstar Labs" /></label>
          <label>Role<input value={role} onChange={(event) => setRole(event.target.value)} placeholder="e.g. Product Designer" /></label>
          <label>Site Link<input value={siteLink} onChange={(event) => setSiteLink(event.target.value)} placeholder="e.g. https://company.com/jobs" /></label>
          <button type="submit">Add to tracker <span>+</span></button>
        </form>

        <div className="list-panel">
          <div className="panel-heading"><span className="panel-number">02</span><h2>Current applications</h2><span className="count">{applications.length}</span></div>
          {applications.length === 0 ? <div className="empty-state"><strong>Your board is clear.</strong><span>New applications will appear here.</span></div> : <div className="application-list">{applications.map((application, index) => <article className="application" key={`${application.company}-${index}`}><div><strong>{application.company}</strong><span>{application.role}</span></div><span className="pill">{application.status}</span></article>)}</div>}
        </div>
      </section>
    </main>
  )
}

export default App
