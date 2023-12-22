import React from 'react';
import { render, screen, fireEvent, act } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { BrowserRouter as Router, useHistory } from 'react-router-dom';

// Mock the global context
jest.mock('./path-to-global-context', () => ({
  ...jest.requireActual('./path-to-global-context'),
  GlobalContext: {
    paymentTask: { paymentTasksData: { paymentTasks: { midAccountDetails: { payAceeptanceAvailableDate: '2023-12-15' } } } },
    callback: jest.fn(),
    aemResponse: {},
  },
}));

import DownloadTalech from './DownloadTalech';

describe('DownloadTalech component', () => {
  it('renders without errors', () => {
    render(<Router><DownloadTalech /></Router>);
    expect(screen.getByText(/Download talech Mobile/i)).toBeInTheDocument();
  });

  it('displays correct date from the global context', () => {
    render(<Router><DownloadTalech /></Router>);
    expect(screen.getByText(/Pay Acceptance Available Date: 2023-12-15/i)).toBeInTheDocument();
  });

  it('handles "mark as done" button click and opens modal', () => {
    render(<Router><DownloadTalech /></Router>);
    act(() => {
      fireEvent.click(screen.getByText(/mark as done/i));
    });
    expect(screen.getByText(/header/i)).toBeInTheDocument();
  });

  it('handles "save for later" button click and redirects to "/"', () => {
    const historyMock = { push: jest.fn() };
    render(
      <Router>
        <DownloadTalech />
      </Router>,
      { wrapper: ({ children }) => <Router history={historyMock}>{children}</Router> }
    );

    act(() => {
      fireEvent.click(screen.getByText(/save for later/i));
    });

    expect(historyMock.push).toHaveBeenCalledWith('/');
  });

  it('opens modal when an image link is clicked', () => {
    render(<Router><DownloadTalech /></Router>);

    act(() => {
      fireEvent.click(screen.getByAltText(/google/i));
    });

    expect(screen.getByText(/header/i)).toBeInTheDocument();
  });

  it('handles "Continue" button click and performs the expected actions', () => {
    render(<Router><DownloadTalech /></Router>);

    act(() => {
      fireEvent.click(screen.getByText(/mark as done/i));
    });

    act(() => {
      fireEvent.click(screen.getByText(/save/i));
    });

    expect(screen.getByText(/header/i)).toBeInTheDocument();
    // Add more assertions based on the expected behavior after "Continue" is clicked
  });

  it('handles "Cancel" button click and closes the modal', () => {
    render(<Router><DownloadTalech /></Router>);

    act(() => {
      fireEvent.click(screen.getByText(/mark as done/i));
    });

    act(() => {
      fireEvent.click(screen.getByText(/cancel/i));
    });

    expect(screen.queryByText(/header/i)).not.toBeInTheDocument();
  });

  it('handles download success data and performs the expected actions', () => {
    render(<Router><DownloadTalech /></Router>);

    act(() => {
      DownloadTalech.defaultProps.downloadSuccessData({});
    });

    expect(screen.queryByText(/header/i)).not.toBeInTheDocument();
    // Add more assertions based on the expected behavior after download success data
  });

  it('handles Save Later button click and redirects to "/"', () => {
    const historyMock = { push: jest.fn() };
    render(
      <Router>
        <DownloadTalech />
      </Router>,
      { wrapper: ({ children }) => <Router history={historyMock}>{children}</Router> }
    );

    act(() => {
      fireEvent.click(screen.getByText(/save for later/i));
    });

    expect(historyMock.push).toHaveBeenCalledWith('/');
    // Add more assertions based on the expected behavior after Save Later button is clicked
  });

  // Add more test cases as needed for other functionalities of the component
});
